package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;

/**
 * Created by MinhDN on 26/4/2018.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>  {
    private List<NotificationResponse.Result> datalist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, noiDung;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.item_tieu_de);
            time = view.findViewById(R.id.item_time);
            noiDung = view.findViewById(R.id.item_noi_dung);
        }
    }

    public NotificationAdapter(Context mContext, List<NotificationResponse.Result> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MyViewHolder(inflater.inflate(R.layout.custom_thong_bao, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        Typeface face2= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");
        NotificationResponse.Result item = datalist.get(position);
        if(AppUtil.checkNull(item.getTitle())){
            holder.title.setText(item.getTitle());
            holder.title.setTypeface(face2);
        }
        if(AppUtil.checkNull(item.getNoiDungChiTiet())){
            holder.noiDung.setText(item.getMessage());
            holder.noiDung.setTypeface(face);
        }

        if(AppUtil.checkNull(item.getNgayGioPush())){
//            String date = AppUtil.convertDateToString(item.getNgayGioPush(), DATE_FORMAT);
            holder.time.setText(item.getNgayGioPush());
            holder.time.setTypeface(face);
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

