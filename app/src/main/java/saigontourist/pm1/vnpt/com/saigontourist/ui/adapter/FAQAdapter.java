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

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {
    private List<FAQResponse.Dsbinhluan> datalist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_no, item_question, item_answer;

        public MyViewHolder(View view) {
            super(view);
            item_no = itemView.findViewById(R.id.item_no);
            item_question = itemView.findViewById(R.id.item_question);
            item_answer = itemView.findViewById(R.id.item_answer);
        }
    }

    public FAQAdapter(Context mContext, List<FAQResponse.Dsbinhluan> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public FAQAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new FAQAdapter.MyViewHolder(inflater.inflate(R.layout.custom_item_faq, parent, false));
    }


    @Override
    public void onBindViewHolder(final FAQAdapter.MyViewHolder holder, int position) {
        final FAQResponse.Dsbinhluan newItem = datalist.get(position);
        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        final Typeface face2= Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaHeavy.TTF");

        if (AppUtil.checkNull(newItem.getSTT())){
            String stt = String.valueOf(newItem.getSTT()) + ".";
            holder.item_no.setText(stt);
            holder.item_no.setTypeface(face);
        }
        if (AppUtil.checkNull(newItem.getTieuDe())){
            holder.item_question.setText(newItem.getTieuDe());
            holder.item_question.setTypeface(face2);
        }
        if (AppUtil.checkNull(newItem.getTieuDe())){
            holder.item_answer.setText(newItem.getNoiDung());
            holder.item_answer.setTypeface(face);
        }


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }



}
