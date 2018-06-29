package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryVpointResult;


public class CategorySpecialAdapter extends BaseAdapter {
    Integer row_index = 0;
    List<DataCategoryResult> objects;
    Context mContext;
    int typeCategory;

    public void setTypeCategory(int typeCategory) {
        this.typeCategory = typeCategory;
    }

    public CategorySpecialAdapter(Context context, List<DataCategoryResult> objects, int typeCategory) {
        mContext = context;
        this.objects = objects;
        this.typeCategory = typeCategory;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_categoryspecial_listview, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //set fonts
        Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/SFUFuturaBook.TTF");
        holder.tvNameValue.setTypeface(face);
        holder.tvNameValue.setText(getItem(position).getName());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.checkboxChecked.setVisibility(View.VISIBLE);
            EventBus.getDefault().postSticky(new MessageEvent.MessageCategorySpecial(getItem(position).getId(),
                    getItem(position).getName(), typeCategory));

        } else {
            holder.checkboxChecked.setVisibility(View.GONE);
        }
        return view;
    }

    public static boolean change() {
        return false;
    }

    static class ViewHolder {
        public TextView tvNameValue;
        public LinearLayout checkboxChecked;
        public LinearLayout llItem, img_merchant_check;

        public ViewHolder(View view) {
            tvNameValue = view.findViewById(R.id.tv_value);
            checkboxChecked = view.findViewById(R.id.checkbox_checked);
            llItem = view.findViewById(R.id.ll_item);
        }
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @Nullable
    @Override
    public DataCategoryResult getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
