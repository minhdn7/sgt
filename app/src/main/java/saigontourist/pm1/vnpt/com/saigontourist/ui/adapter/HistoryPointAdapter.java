package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;



/**
 * Created by MinhDN on 23/4/2018.
 */
public class HistoryPointAdapter extends RecyclerView.Adapter<HistoryPointAdapter.MyViewHolder> {
    private List<HistoryPointResponse.Datum> datalist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvData, tvPoint, tvAddress, tvDateTransfer;
        public TextView tvLabelData, tvLabelPoint, tvLabelAddress, tvLabelDateTransfer;

        public MyViewHolder(View view) {
            super(view);
            tvLabelData = view.findViewById(R.id.tv_label_data);
            tvLabelPoint = view.findViewById(R.id.tv_label_point);
            tvLabelAddress = view.findViewById(R.id.tv_label_address);
            tvLabelDateTransfer = view.findViewById(R.id.tv_label_date_transfer);

            tvData = view.findViewById(R.id.tv_data);
            tvPoint = view.findViewById(R.id.tv_point);
            tvAddress = view.findViewById(R.id.tv_address);
            tvDateTransfer = view.findViewById(R.id.tv_date_transfer);
        }
    }

    public HistoryPointAdapter(Context mContext, List<HistoryPointResponse.Datum> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public HistoryPointAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HistoryPointAdapter.MyViewHolder(inflater.inflate(R.layout.custom_history_point, parent, false));
    }


    @Override
    public void onBindViewHolder(final HistoryPointAdapter.MyViewHolder holder, int position) {
        final HistoryPointResponse.Datum newItem = datalist.get(position);
        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        final Typeface face2= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");
        holder.tvLabelData.setTypeface(face);
        holder.tvLabelPoint.setTypeface(face);
        holder.tvLabelAddress.setTypeface(face);
        holder.tvLabelDateTransfer.setTypeface(face);

        if (AppUtil.checkNull(newItem.getLyDo())){
            holder.tvData.setText(String.valueOf(newItem.getLyDo()));
            holder.tvData.setTypeface(face2);
        }
        if (AppUtil.checkNull(newItem.getDiem())){
            holder.tvPoint.setText(String.valueOf((newItem.getDiem()) + " P"));
            holder.tvPoint.setTypeface(face2);

        }
        if (AppUtil.checkNull(newItem.getDiaDiemGiaoDich())){
            holder.tvAddress.setText(String.valueOf(newItem.getDiaDiemGiaoDich()));
            holder.tvAddress.setTypeface(face2);
        }
        if (AppUtil.checkNull(newItem.getNgay())){
            holder.tvDateTransfer.setText(String.valueOf(newItem.getNgay()));
            holder.tvDateTransfer.setTypeface(face2);
        }

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }



}

