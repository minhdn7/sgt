package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;

/**
 * Created by MinhDN on 20/4/2018.
 */
public class HousePointAdapter extends RecyclerView.Adapter<HousePointAdapter.MyViewHolder> {
    private List<AccountInfoResponse.ThongTinDiem> datalist;
    private Context mContext;

    AlertDialog.Builder yesDialog;
    AlertDialog dialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView soDiem, phamVi, loaiDiem, hanSuDung, stt;
        public LinearLayout ln_item_point;

        public MyViewHolder(View view) {
            super(view);
            soDiem = view.findViewById(R.id.so_diem);
            phamVi = view.findViewById(R.id.pham_vi);
            stt = view.findViewById(R.id.stt);
            hanSuDung = view.findViewById(R.id.han_su_dung);
            ln_item_point = view.findViewById(R.id.ln_item_point);
        }
    }

    public HousePointAdapter(Context mContext, List<AccountInfoResponse.ThongTinDiem> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MyViewHolder(inflater.inflate(R.layout.custom_item_info_point, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AccountInfoResponse.ThongTinDiem newItem = datalist.get(position);
        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        final Typeface face2= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");

//        if (AppUtil.checkNull(newItem.getSTT())){
//            // chuyển số điểm thành số thứ tự
//            holder.stt.setText(String.valueOf((position + 1)));
//            holder.stt.setTypeface(face2);
//        }
        holder.stt.setText(String.valueOf((position + 1)));
        holder.stt.setTypeface(face2);
        if (AppUtil.checkNull(newItem.getPhamVi())){
            holder.phamVi.setTypeface(face);
            holder.phamVi.setText(String.valueOf(newItem.getPhamVi()));
        }
        if (AppUtil.checkNull(newItem.getSoDiem())){
            // chuyển loại điểm thành số điểm
            holder.soDiem.setText(String.valueOf(newItem.getSoDiem()));
            holder.soDiem.setTypeface(face2);

        }
        if (AppUtil.checkNull(newItem.getHanSuDung())){
            holder.hanSuDung.setText(newItem.getHanSuDung());
            holder.hanSuDung.setTypeface(face);
        }

        holder.ln_item_point.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(mContext);
                View pointDialogView = factory.inflate(R.layout.custom_item_point_click, null);
                yesDialog = new AlertDialog.Builder(mContext);
                yesDialog.setCancelable(true);
                yesDialog.setView(pointDialogView);

                TextView text = pointDialogView.findViewById(R.id.text);
                TextView text1 = pointDialogView.findViewById(R.id.text1);
                Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
                text1.setTypeface(face);
                text.setTypeface(face);

                if(newItem.getPhamVi() != null){
                    text.setText(mContext.getString(R.string.str_diem_ky_han));
                    text1.setText(mContext.getString(R.string.str_diem_vpoint));
                    text1.setTypeface(face);
//                    if(newItem.getPointTypeCode() == 1){
//                        text.setText(mContext.getString(R.string.str_diem_ky_han));
//                        text1.setText(mContext.getString(R.string.str_diem_vpoint));
//                        text1.setTypeface(face);
//                    } else if (newItem.getPointTypeCode() == 0){
//                        text.setText(mContext.getString(R.string.str_diem_doanh_nghiep));
//                        text1.setText(mContext.getString(R.string.str_diem_doanhnghiep));
//                        text1.setTypeface(face);
//                    }
                }

                dialog = yesDialog.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }



}
