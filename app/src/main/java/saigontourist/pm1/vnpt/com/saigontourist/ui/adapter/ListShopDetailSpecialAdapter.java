package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataShopDetail;


public class ListShopDetailSpecialAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Activity mActivity;
    private List<DataShopDetail> mDataSet;
    private LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    public ListShopDetailSpecialAdapter(Context context, Activity activity, List<DataShopDetail> dataSet) {
        mContext = context;
        mActivity = activity;
        mDataSet = dataSet;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item_list_shop_detail_, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        ViewHolder holder = (ViewHolder) h;

        if (mDataSet != null && 0 <= position && position < mDataSet.size()) {
            if (mDataSet.get(position) instanceof DataDetailSpecialVpointResult.DataListShopByNewResult.DataShop) {
                final DataDetailSpecialVpointResult.DataListShopByNewResult.DataShop newItem =
                        (DataDetailSpecialVpointResult.DataListShopByNewResult.DataShop) mDataSet.get(position);

                // Use ViewBindHelper to restore and save the open/close state of the SwipeRevealView
                // put an unique string id as value, can be any string which uniquely define the data
                binderHelper.bind(holder.swipeLayout, String.valueOf(newItem.getId()));

                final Double lat = newItem.getLatitude();
                final Double lon = newItem.getLongitude();
                holder.locationLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Lấy lat-lon, Chuyển sang map -> vẽ bản đồ chỉ đường
                        if (lat != 0.0 && lon != 0.0) {
//                        EventBus.getDefault().postSticky(new LatLonListShopChiTietUuDaiEvent(lat, lon));
//                        mContext.startActivity(new Intent(mContext, MapChiTietUuDaiActivity.class));
                        } else {
                            Toast.makeText(mContext, "Không lấy được vị trí cửa hàng.", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                holder.callShop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                mContext.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + newItem.getPhone())));
                            } else {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CALL_PHONE)) {
//                                Toast.makeText(mContext, "App requires Phone Call permission.\nPlease allow that in the device settings.", Toast.LENGTH_LONG).show();
                                }
                                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CALL_PHONE}, AppDef.REQUEST_LOCATION);
                            }
                        } else {
                            mContext.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + newItem.getPhone())));
                        }
                    }
                });

                holder.textView.setText(newItem.getName());
            } else if (mDataSet.get(position) instanceof DataDetailSpecialSaigonResult.Danhsachcuahang) {
                DataDetailSpecialSaigonResult.Danhsachcuahang itemData = (DataDetailSpecialSaigonResult.Danhsachcuahang) mDataSet.get(position);
                binderHelper.bind(holder.swipeLayout, String.valueOf(itemData.getTenDonVi()));
                holder.textView.setText(itemData.getTenDonVi());
            }

        }
    }

    @Override
    public int getItemCount() {
        if (mDataSet == null)
            return 0;
        return mDataSet.size();
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeLayout;
        private TextView textView;
        private ImageView locationLayout, callShop;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            locationLayout = itemView.findViewById(R.id.item_location);
            callShop = itemView.findViewById(R.id.item_call);
            textView = itemView.findViewById(R.id.text);
            Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
            textView.setTypeface(face);
        }

    }
}
