package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;


public class CategorySearchFilterAdapter extends RecyclerView.Adapter {
    Integer row_index = 0;
    List<DataCategoryResult> objects;
    Context mContext;
    int typeCategory;

    public void setTypeCategory(int typeCategory) {
        this.typeCategory = typeCategory;
    }

    public CategorySearchFilterAdapter(Context context, List<DataCategoryResult> objects, int typeCategory) {
        mContext = context;
        this.objects = objects;
        this.typeCategory = typeCategory;
    }


    public static boolean change() {
        return false;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameValue;
        public LinearLayout checkboxChecked;
        public LinearLayout llItem;

        public ViewHolder(View view) {
            super(view);
            tvNameValue = view.findViewById(R.id.tv_value);
            checkboxChecked = view.findViewById(R.id.checkbox_checked);
            llItem = view.findViewById(R.id.ll_item);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_categoryspecial_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        holder.tvNameValue.setTypeface(face);
        holder.tvNameValue.setText(objects.get(position).getName());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.checkboxChecked.setVisibility(View.VISIBLE);
            EventBus.getDefault().postSticky(new MessageEvent.MessageCategorySpecial(objects.get(position).getId(),
                    objects.get(position).getName(), typeCategory));

        } else {
            holder.checkboxChecked.setVisibility(View.GONE);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }
}
