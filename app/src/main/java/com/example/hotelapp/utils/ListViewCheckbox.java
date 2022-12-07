package com.example.hotelapp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.R;
import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.entities.RoomType;

import java.util.List;

public class ListViewCheckbox extends BaseAdapter {
    private Context context;
    private List<Amenity> list;
    private AppDatabase db;
    LayoutInflater mInflater;

    public ListViewCheckbox(Context context, List<Amenity> list) {
        this.context = context;
        this.list = list;
        db=AppDatabase.getInstance(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        final ListViewCheckbox.ViewHolder holder;
        convertView = null;
        if (convertView == null) {
            holder = new ListViewCheckbox.ViewHolder();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_with_checkbox, null);
            holder.amenityName = (TextView) convertView.findViewById(R.id.list_view_adapter_amenityName);
            holder.amenityName.setText(list.get(position).getAmenityName());

            holder.price=(TextView) convertView.findViewById(R.id.list_view_adapter_amenityPrice);
            holder.price.setText(Float.toString(list.get(position).getPrice()));

            holder.checked = (CheckBox) convertView
                    .findViewById(R.id.list_view_adapter_checked);
            holder.checked.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ListViewCheckbox.ViewHolder) convertView.getTag();
        }
        int tag_position = (Integer) holder.checked.getTag();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        return convertView;
    }

    class ViewHolder {
        TextView amenityName;
        TextView price;
        CheckBox checked;
    }
}
