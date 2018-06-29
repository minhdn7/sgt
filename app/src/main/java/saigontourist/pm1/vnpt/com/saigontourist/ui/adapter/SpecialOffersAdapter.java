package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.SaiGonTouristApplication;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersObject;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.DetailSpecialOffersActivity;
import timber.log.Timber;


public class SpecialOffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SpecialOffersObject> datalist;
    private Context mContext;
    int idAvatart;

    @Inject
    LoginUserCookies loginUserCookies;

    public final int TYPE_NEW = 0;
    public final int TYPE_LOAD = 1;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public SpecialOffersAdapter(Context mContext, List<SpecialOffersObject> datalist) {
        ((SaiGonTouristApplication) mContext.getApplicationContext()).inject(this);
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_NEW) {
            return new NewHolder(inflater.inflate(R.layout.custom_item_new, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= (getItemCount() - 1) && getItemCount() > 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_NEW) {
            ((NewHolder) holder).bindData(datalist.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (datalist.get(position).getTypeView() == 0) {
            return TYPE_NEW;
        } else {
            return TYPE_LOAD;
        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void removeAll() {
        int size = this.datalist.size();
        this.datalist.clear();
        notifyItemRangeRemoved(0, size);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }


    class NewHolder extends RecyclerView.ViewHolder {
        public TextView tenDanhMuc, tenMerchant, tieuDe, moTa, percent, tvType;
        public ImageView imgShareFace;
        public ImageView imageView;
        public LinearLayout imgLoading;
        public LinearLayout lo_item_uu_dai;
        public LinearLayout ln_tichdiem;
        public LinearLayout layout_imgView;
        GlideUrl glideUrl;

        public NewHolder(View view) {
            super(view);
            tenDanhMuc = view.findViewById(R.id.item_ten_danhmuc);
            tenMerchant = view.findViewById(R.id.item_ten_merchant);
            tieuDe = view.findViewById(R.id.item_tieu_de);
            moTa = view.findViewById(R.id.item_mo_ta);
            percent = view.findViewById(R.id.percent);
            tvType = view.findViewById(R.id.tv_type);
            imageView = view.findViewById(R.id.imageViewItem);
            imgShareFace = view.findViewById(R.id.imgShareFace);
            lo_item_uu_dai = view.findViewById(R.id.lo_item_uu_dai);
            layout_imgView = view.findViewById(R.id.layout_imgView);
            imgLoading = view.findViewById(R.id.imgLoading);
            ln_tichdiem = view.findViewById(R.id.ln_tichdiem);

            //set chieu cao bang 16:9 chieu rong cua anh
            ViewGroup.LayoutParams params = layout_imgView.getLayoutParams();
            params.width = Resources.getSystem().getDisplayMetrics().widthPixels;
            params.height = params.width * 9 / 16;
            layout_imgView.setLayoutParams(params);
//            Log.d("Thao", params.width + ":" + params.height);
        }

        void bindData(final SpecialOffersObject item) {
            Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
            Typeface FACE = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");
            if (item instanceof SpecialOffersVpointResult.DataOffersVpoint) {
                imgShareFace.setVisibility(View.VISIBLE);
                final SpecialOffersVpointResult.DataOffersVpoint newItem = (SpecialOffersVpointResult.DataOffersVpoint) item;
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getCategoryName())) {
                    tenDanhMuc.setText(newItem.getCategoryName());
                    tenDanhMuc.setTypeface(face);
                    tenDanhMuc.setSelected(true);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getMerchantName())) {
                    tenMerchant.setText(newItem.getMerchantName());
                    tenMerchant.setTypeface(face);
                    tenMerchant.setSelected(true);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getTitle())) {
                    tieuDe.setText(newItem.getTitle());
                    tieuDe.setTypeface(FACE);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getExcerpt())) {
                    moTa.setText(newItem.getExcerpt());
                    moTa.setTypeface(face);
                }

                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getAllocation())) {
                    if (!newItem.getAllocation().contentEquals("")) {
                        percent.setText(newItem.getAllocation().split("%")[0]);
                        percent.setTypeface(face);
//                    Log.d("Thao", newItem.getAllocation().split("%")[0]);
                    } else {
                        ln_tichdiem.setVisibility(View.GONE);
                    }
                }
                int idAvatart = newItem.getAvatar();
                // loading album cover using Glide library
                String urlImage = mContext.getString(R.string.api_base_url_vpoint) + StringUtils.GET_IMAGE_NEWS_URL + idAvatart;


                HashSet<String> cookies = loginUserCookies.get();
                StringBuilder sb = new StringBuilder();
                for (String cookie : cookies) {
                    sb.append(cookie).append("; ");
                }

                if (sb.length() > 0) {
                    Timber.tag("Cookies").d("sent: %s", sb.toString());
                    glideUrl = new GlideUrl(urlImage, new LazyHeaders.Builder()
                            .addHeader(StringUtils.COOKIE, sb.toString())
                            .build());
                }
                // Load image via Glide lib using context
                if (isValidContextForGlide(mContext)) {
                    Glide.with(mContext)
                            .load(glideUrl)
                            .apply(new RequestOptions().signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    imgLoading.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    imgLoading.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(imageView);

                }

                imgShareFace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getShareLink())) {
                            onShareFacebook(newItem.getShareLink());
                        }
                    }
                });

                lo_item_uu_dai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().postSticky(new MessageEvent.MessageIdDetailAtivity(newItem.getId(), 1, newItem.getMerchantId()));
                        mContext.startActivity(new Intent(mContext, DetailSpecialOffersActivity.class));
                    }
                });
            }
            //Item SaigonTourist
            else if (item instanceof SpecialOffersSaigonResult.SpecialOffer) {
                imgShareFace.setVisibility(View.GONE);
                final SpecialOffersSaigonResult.SpecialOffer newItem = (SpecialOffersSaigonResult.SpecialOffer) item;
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getLinhVuc())) {
                    tenDanhMuc.setText(newItem.getLinhVuc());
                    tenDanhMuc.setTypeface(face);
                    tenDanhMuc.setSelected(true);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getTenDonVi())) {
                    tenMerchant.setText(newItem.getTenDonVi());
                    tenMerchant.setTypeface(face);
                    tenMerchant.setSelected(true);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getTenChuongTrinhUuDai())) {
                    tieuDe.setText(newItem.getTenChuongTrinhUuDai());
                    tieuDe.setTypeface(FACE);
                }
                if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getTyLeGiam())) {
                    tvType.setText(newItem.getTyLeGiam());
                    tvType.setTypeface(FACE);
                }

                try {
                    percent.setText(formatDouble(newItem.getTyLeUuDai()));
                    percent.setTypeface(face);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String urlImage = mContext.getString(R.string.api_base_url_saigon_image) + newItem.getDuongDanAnh();

                if (isValidContextForGlide(mContext)) {
                    Glide.with(mContext)
                            .load(urlImage)
                            .apply(new RequestOptions()
                                    .signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
                            )
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    imgLoading.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    imgLoading.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(imageView);


                }


                imgShareFace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (!org.apache.commons.lang3.StringUtils.isEmpty(newItem.getShareLink())) {
//                            onShareFacebook(newItem.getShareLink());
//                        }
                    }
                });

                lo_item_uu_dai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().postSticky(new MessageEvent.MessageIdDetailAtivity(newItem.getChuongTrinhUuDaiId(), 0, newItem.getChuongTrinhUuDaiId()));
                        mContext.startActivity(new Intent(mContext, DetailSpecialOffersActivity.class));
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void onShareFacebook(String link) {
        List<Intent> targetShareIntents = new ArrayList<>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        List<ResolveInfo> resInfos = mContext.getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfos.isEmpty()) {
            for (ResolveInfo resInfo : resInfos) {
                String packageName = resInfo.activityInfo.packageName;
                if (packageName.contains("com.facebook.katana")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, link);
                    intent.putExtra(Intent.EXTRA_SUBJECT, link);
                    intent.setPackage(packageName);
                    targetShareIntents.add(intent);
                }
            }
            if (!targetShareIntents.isEmpty()) {
                Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "Choose app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                mContext.startActivity(chooserIntent);
            } else {
                Toast.makeText(mContext, "Bạn chưa cài đặt Facebook.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }

    public static String formatDouble(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
