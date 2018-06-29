package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataShopDetail;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.ListShopDetailSpecialAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.DetailSpecialOffersPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.DetailSpecialOffersView;
import timber.log.Timber;


public class DetailSpecialOffersActivity extends BaseActivity implements DetailSpecialOffersView {
    @BindView(R.id.item_ten_danhmuc)
    TextView itemTenDanhmuc;
    @BindView(R.id.item_ten_merchant)
    TextView itemTenMerchant;
    @BindView(R.id.imageViewItem)
    ImageView imageViewItem;
    @BindView(R.id.item_tieu_de)
    TextView itemTieuDe;
    @BindView(R.id.item_created_date)
    TextView itemCreatedDate;
    @BindView(R.id.item_end_date)
    TextView itemEndDate;
    @BindView(R.id.item_content)
    WebView itemContent;
    @BindView(R.id.lo_list_shop)
    LinearLayout loListShop;
    @BindView(R.id.imageViewShop)
    ImageView imageViewShop;
    @BindView(R.id.tv_thong_tin_doanh_nghiep)
    TextView tvThongTinDoanhNghiep;

    private static final String TAG = DetailSpecialOffersActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.rcvDanhSachCuaHang)
    RecyclerView rcvDanhSachCuaHang;
    @BindView(R.id.ll_add_fields)
    LinearLayout llAddFields;
    private List<DataDetailSpecialVpointResult.DetailNewResponse> dataDetailNewResponses;
    private List<DataShopDetail> dataListShopByNewResponses;
    private DataDetailSpecialSaigonResult dataDetailSpecialSaigonResults;

    private boolean isShowListShop = false;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.tv_cuahang_ganban)
    TextView tvCuahangGanban;
    @BindView(R.id.imgShareFaceTitle)
    ImageView imgShareFaceTitle;

    private String shareLink;
    private Integer applyType;

    @Inject
    LoginUserCookies loginUserCookies;
    @Inject
    DetailSpecialOffersPresenter detailPresenter;
    ListShopDetailSpecialAdapter adapter;
    private int idDetail = 0;
    private int TYPE_DETAIL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_special_offers);

        initView();
        initListener();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void initView() {
        setTitleToobar("Chi tiết ưu đãi");
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        Typeface FACE = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaHeavy.TTF");
        itemTenDanhmuc.setTypeface(face);
        itemTenMerchant.setTypeface(face);
        text2.setTypeface(FACE);
        itemTieuDe.setTypeface(FACE);
        tvThongTinDoanhNghiep.setTypeface(FACE);
        detailPresenter.setView(this);
        detailPresenter.onViewCreate();
        setupListShopByNew();
    }


    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater factory = LayoutInflater.from(DetailSpecialOffersActivity.this);
                final View deleteDialogView = factory.inflate(R.layout.custom_dialog_share_detail, null);
                final AlertDialog yesDialog = new AlertDialog.Builder(DetailSpecialOffersActivity.this).create();
                yesDialog.setView(deleteDialogView);

                TextView tvTextView = deleteDialogView.findViewById(R.id.tvTextView);
                Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
                tvTextView.setTypeface(face);

                //face
                deleteDialogView.findViewById(R.id.imgFace).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onShareFacebook(shareLink);
                    }
                });
                // danh ba
                deleteDialogView.findViewById(R.id.img_DanhBa).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setShareContact();
                    }
                });

                // tin nhắn facebook
                deleteDialogView.findViewById(R.id.img_tin_nhan_face).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setShareSMSFacebook();
                    }
                });

                //more
                deleteDialogView.findViewById(R.id.img_Share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setShareMore();
                    }
                });

                yesDialog.show();
            }
        });
    }

    private void setShareSMSFacebook() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent
                .putExtra(Intent.EXTRA_TEXT, shareLink);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.facebook.orca");
        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Chưa cài đặt Facebook Messenger", Toast.LENGTH_LONG).show();
        }
    }


    private void showDetailNew() {
        switch (TYPE_DETAIL) {
            case 0:
                if (dataDetailSpecialSaigonResults != null) {
                    if (dataDetailSpecialSaigonResults.getData() != null &&
                            dataDetailSpecialSaigonResults.getData().getChitietuudai().size() > 0) {
                        DataDetailSpecialSaigonResult.Chitietuudai dataChitietUuDai = dataDetailSpecialSaigonResults.getData().getChitietuudai().get(0);

                        text2.setText(getResources().getString(R.string.str_danhsach_cuahang_uudai));

                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getLinhVuc())) {
                            if (dataChitietUuDai.getLinhVuc().split(";").length > 1) {
                                itemTenDanhmuc.setVisibility(View.GONE);
                                llAddFields.setVisibility(View.VISIBLE);
                                for (int i = 0; i < dataChitietUuDai.getLinhVuc().split(";").length; i++) {
                                    addLayoutField(dataChitietUuDai.getLinhVuc().split(";")[i]);
                                }

                            } else {
                                llAddFields.setVisibility(View.GONE);
                                itemTenDanhmuc.setVisibility(View.VISIBLE);
                                itemTenDanhmuc.setText(dataChitietUuDai.getLinhVuc());
                            }
                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getTenDonVi())) {
                            itemTenMerchant.setText(dataChitietUuDai.getTenDonVi());
                            itemTenMerchant.setSelected(true);
                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getTenChuongTrinhUuDai())) {
                            itemTieuDe.setText(dataChitietUuDai.getTenChuongTrinhUuDai());
                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getNgaybatdau())) {
                            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
                            itemCreatedDate.setTypeface(face);
                            itemCreatedDate.setText("Ngày bắt đầu: " + dataChitietUuDai.getNgaybatdau());

                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getNgayketthuc())) {
                            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
                            itemEndDate.setVisibility(View.VISIBLE);
                            itemEndDate.setTypeface(face);
                            itemEndDate.setText("Ngày kết thúc: "
                                    + dataChitietUuDai.getNgayketthuc());

                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getMoTa())) {
                            itemContent.loadDataWithBaseURL("", StringUtils.START_HTML + dataChitietUuDai.getMoTa() + StringUtils.END_HTML, "text/html", "UTF-8", "");
                        }
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(dataChitietUuDai.getDuongDanAnh())) {
                            String urlImage = getResources().getString(R.string.api_base_url_saigon_image) + dataChitietUuDai.getDuongDanAnh();
                            Glide.with(DetailSpecialOffersActivity.this)
                                    .load(urlImage)
                                    .apply(new RequestOptions().signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))))
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            return false;
                                        }
                                    })
                                    .into(imageViewItem);
                        }

                    }
                    if (dataDetailSpecialSaigonResults.getData().getDanhsachcuahang() != null &&
                            dataDetailSpecialSaigonResults.getData().getDanhsachcuahang().size() > 0) {
                        dataListShopByNewResponses.clear();
                        dataListShopByNewResponses.addAll(dataDetailSpecialSaigonResults.getData().getDanhsachcuahang());
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            case 1:
                if (dataDetailNewResponses != null) {
                    llAddFields.setVisibility(View.GONE);
                    itemEndDate.setVisibility(View.GONE);
                    DataDetailSpecialVpointResult.DetailNewResponse detailNewResponse = dataDetailNewResponses.get(0);
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(detailNewResponse.getApplyText())) {
                        text2.setText(detailNewResponse.getApplyText());
                    }

                    if (!org.apache.commons.lang3.StringUtils.isEmpty(detailNewResponse.getCategoryName())) {
                        itemTenDanhmuc.setText(detailNewResponse.getCategoryName());
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(detailNewResponse.getMerchantName())) {
                        itemTenMerchant.setText(detailNewResponse.getMerchantName());
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(detailNewResponse.getTitle())) {
                        itemTieuDe.setText(detailNewResponse.getTitle());
                    }
                    if (detailNewResponse.getCreatedDate() != null) {
                        String date = convertDateToString(detailNewResponse.getCreatedDate(), StringUtils.DATE_FORMAT_DETAIL);
                        itemCreatedDate.setText(getString(R.string.str_ngay_dang_tin) + date);
                        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
                        itemCreatedDate.setTypeface(face);
                    }
                    if (detailNewResponse.getContent() != null) {

                        itemContent.loadDataWithBaseURL("", StringUtils.START_HTML + detailNewResponse.getContent() + StringUtils.END_HTML, "text/html", "UTF-8", "");
                    }
                    if (detailNewResponse.getAvatar() != null) {
                        GlideUrl glideUrl = null;
                        String urlImage = getString(R.string.api_base_url_vpoint) + StringUtils.GET_IMAGE_NEWS_URL + detailNewResponse.getAvatar();
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
                        Glide.with(DetailSpecialOffersActivity.this)
                                .load(glideUrl)
                                .apply(new RequestOptions().signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                })
                                .into(imageViewItem);
                    }

                    if (detailNewResponse.getShareLink() != null) {
                        shareLink = detailNewResponse.getShareLink();
                    }
                }
                break;
        }

    }

    private void setupListShopByNew() {
        dataListShopByNewResponses = new ArrayList<>();
        rcvDanhSachCuaHang.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListShopDetailSpecialAdapter(this, DetailSpecialOffersActivity.this, dataListShopByNewResponses);
        rcvDanhSachCuaHang.setAdapter(adapter);
    }


    @OnClick({R.id.lo_list_shop, R.id.tv_thong_tin_doanh_nghiep, R.id.imgShareFaceTitle,
            R.id.tv_cuahang_ganban})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.lo_list_shop:
                switch (TYPE_DETAIL) {
                    case 0:
                        if (isShowListShop) {
                            rcvDanhSachCuaHang.setVisibility(View.GONE);
                            imageViewShop.setImageResource(R.drawable.ic_keyboard_arrow_right_black_36dp);
                            isShowListShop = false;
                        } else {
                            rcvDanhSachCuaHang.setVisibility(View.VISIBLE);
                            imageViewShop.setImageResource(R.drawable.ic_keyboard_arrow_down_black_36dp);
                            isShowListShop = true;
                        }
                        break;
                    case 1:
                        if (applyType == 2) {
                            if (dataListShopByNewResponses != null) {
                                if (isShowListShop) {
                                    rcvDanhSachCuaHang.setVisibility(View.GONE);
                                    imageViewShop.setImageResource(R.drawable.ic_keyboard_arrow_right_black_36dp);
                                    isShowListShop = false;
                                } else {
                                    rcvDanhSachCuaHang.setVisibility(View.VISIBLE);
                                    imageViewShop.setImageResource(R.drawable.ic_keyboard_arrow_down_black_36dp);
                                    isShowListShop = true;
                                }
                            } else {
//                    Toast.makeText(getApplicationContext(), R.string.str_chua_co_mo_ta, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            imageViewShop.setVisibility(View.INVISIBLE);
                        }
                        break;
                }
                break;
            case R.id.tv_thong_tin_doanh_nghiep:
                startActivity(new Intent(this, InfoEnterpriseActivity.class));
                break;
            case R.id.tv_cuahang_ganban:
                MessageEvent.ShopOnMapFilter shopOnMapFilter = new MessageEvent.ShopOnMapFilter();
                switch (TYPE_DETAIL) {
                    case 0:
                        shopOnMapFilter.setField(0);
                        shopOnMapFilter.setEnterpriseId(0);
                        shopOnMapFilter.setRadius(10);
                        shopOnMapFilter.setSearchingText("");
                        shopOnMapFilter.setTypeSearch(0);
                        shopOnMapFilter.setChuongTrinhUuDaiId(idDetail);
                        shopOnMapFilter.setKindOfSearch(1);
                        break;
                    case 1:
                        DataDetailSpecialVpointResult.DetailNewResponse detailNewResponse = dataDetailNewResponses.get(0);
                        shopOnMapFilter.setField(detailNewResponse.getFieldId());
                        shopOnMapFilter.setEnterpriseId(detailNewResponse.getMerchantId());
                        shopOnMapFilter.setRadius(10);
                        shopOnMapFilter.setSearchingText(null);
                        shopOnMapFilter.setTypeSearch(1);
                        shopOnMapFilter.setKindOfSearch(1);
                        break;
                }

                EventBus.getDefault().postSticky(shopOnMapFilter);

                startActivity(new Intent(this, SearchFilterLocationActivity.class));
                break;
            case R.id.imgShareFaceTitle:
                onShareFacebook(shareLink);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppDef.REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // da duoc cap quyen
//                    Toast.makeText(getApplicationContext(), R.string.permision_available_camera,
//                            Toast.LENGTH_SHORT).show();
                } else {
                    // khong duoc cap quyen
                    showDialog();
                }
                return;
            }
        }
    }

    private void showDialog() {
        AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("App chưa được cấp quyền sử dụng, bạn có muốn bật không ?")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        alert = builder.create();
        alert.show();
    }

    // -----------------------------action share ------------------------
    public void onShareFacebook(String link) {
        List<Intent> targetShareIntents = new ArrayList<>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(shareIntent, 0);
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
                startActivity(chooserIntent);
            } else {
                showToast("Bạn chưa cài đặt Facebook.");
            }
        }
    }

    private void setShareContact() {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        String shareSub = "Link cài đặt:";
        //"shareLink":
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
    }

    // Call to update the share intent
    private void setShareMore() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareSub = "Link tin mới:";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
        startActivity(Intent.createChooser(sharingIntent, "Chọn app chia sẻ"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Glide.with(DetailSpecialOffersActivity.this).pauseRequests();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    @Override
    public void onGetDetailSpecialVpointSuccses(DataDetailSpecialVpointResult dataResult) {
        hideProgressBar();
        if (dataResult != null && dataResult.getDataDetailNewResponses().size() > 0) {
            dataDetailNewResponses = dataResult.getDataDetailNewResponses();
            applyType = dataResult.getDataDetailNewResponses().get(0).getApplyType();
            if (applyType == 2) {
                imageViewShop.setVisibility(View.VISIBLE);
            } else {
                imageViewShop.setVisibility(View.INVISIBLE);
            }
            showDetailNew();
        }
    }

    @Override
    public void onGetListShopDetailSpecialVpointSuccses(DataDetailSpecialVpointResult.DataListShopByNewResult dataShop) {
        hideProgressBar();
        if (dataShop != null && dataShop.getDataListShopByNewResponses().size() > 0) {
            dataListShopByNewResponses.addAll(dataShop.getDataListShopByNewResponses());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetDetailSpecialTourisSuccses(DataDetailSpecialSaigonResult dataResult) {
        hideProgressBar();
        if (dataResult != null) {
            dataDetailSpecialSaigonResults = dataResult;
            showDetailNew();
        }

    }

    @Override
    public void onGetDetailSpecialVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetListShopDetailSpecialVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetDetailSpecialTourisFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageIdDetailAtivity event) {
        /* Do something */
        if (event != null) {
            idDetail = event.getIdDetail();
            TYPE_DETAIL = event.getTypeDetail();
            switch (event.getTypeDetail()) {
                case 0:
                    //SaigonTouris
                    fab.setVisibility(View.GONE);
                    showProgressBar();
                    getTokenDevIfNeed();
                    break;
                case 1:
                    //Vpoint
                    fab.setVisibility(View.VISIBLE);
                    showProgressBar();
                    detailPresenter.getDetailSpecialVpoint(idDetail);
                    detailPresenter.getListShopDetailSpecialVpoint(idDetail);
                    break;
            }
        }
    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        super.onLoadTokenDevUser(tokenDev);
        hideProgressBar();
        detailPresenter.getDetailSpecialTouris(tokenDev, StringUtils.MaUngDung, idDetail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void addLayoutField(String nameFields) {
        View layout2 = LayoutInflater.from(this).inflate(R.layout.layout_add_item_fields, llAddFields, false);
        TextView textView = (TextView) layout2.findViewById(R.id.tv_item_fields);
        textView.setText(nameFields);
        llAddFields.addView(layout2);
    }
}
