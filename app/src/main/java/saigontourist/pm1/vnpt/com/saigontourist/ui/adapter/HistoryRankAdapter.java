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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;


public class HistoryRankAdapter extends RecyclerView.Adapter<HistoryRankAdapter.MyViewHolder>  {
    private List<HistoryRankResponse.LichSu> datalist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rankName, rankTime, rankNextTime;

        public MyViewHolder(View view) {
            super(view);
            rankName = view.findViewById(R.id.tv_rank_name);
            rankTime = view.findViewById(R.id.tv_rank_accept_time);
            rankNextTime = view.findViewById(R.id.tv_rank_accept_next_time);
        }
    }

    public HistoryRankAdapter(Context mContext, List<HistoryRankResponse.LichSu> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public HistoryRankAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HistoryRankAdapter.MyViewHolder(inflater.inflate(R.layout.item_history_rank, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryRankAdapter.MyViewHolder holder, int position) {
        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        Typeface face2= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");
        HistoryRankResponse.LichSu item = datalist.get(position);
        if(AppUtil.checkNull(item.getTenHangHoiVien())){
            holder.rankName.setText(item.getTenHangHoiVien());
            holder.rankName.setTypeface(face2);
        }
        if(AppUtil.checkNull(item.getThoiGianXetHang())){
            holder.rankTime.setText(item.getThoiGianXetHang());
            holder.rankTime.setTypeface(face);
        }

        if(AppUtil.checkNull(item.getThoiGianXetHangTiepTheo())){
            holder.rankNextTime.setText(item.getThoiGianXetHangTiepTheo());
            holder.rankNextTime.setTypeface(face);
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
