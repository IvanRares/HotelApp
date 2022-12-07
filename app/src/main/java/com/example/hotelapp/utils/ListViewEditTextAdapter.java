package com.example.hotelapp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.R;
import com.example.hotelapp.entities.RoomType;

import java.util.List;

public class ListViewEditTextAdapter extends BaseAdapter {
    private Context context;
    private List<RoomType> list;
    private String startDate;
    private String endDate;
    private AppDatabase db;
    LayoutInflater mInflater;

    public ListViewEditTextAdapter(Context context, List<RoomType> list, String startDate, String endDate) {
        this.context = context;
        this.list = list;
        this.startDate = startDate;
        this.endDate = endDate;
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
        final ViewHolder holder;
        convertView = null;
        if (convertView == null) {
            holder = new ViewHolder();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_with_edittext, null);
            holder.roomType = (TextView) convertView.findViewById(R.id.list_view_adapter_roomType);
            holder.roomType.setText(list.get(position).getRoomTypeName());

            int availableRooms=db.roomTypeDao().getEmptyRoomsForRoomType(startDate,endDate,list.get(position).getRoomTypeId());
            holder.numberOfRoomsAvailable=(TextView) convertView.findViewById(R.id.list_view_adapter_emptyRooms);
            holder.numberOfRoomsAvailable.setText(Integer.toString(availableRooms));

            holder.numberOfRoomsWanted = (EditText) convertView
                    .findViewById(R.id.list_view_adapter_number);
            holder.numberOfRoomsWanted.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int tag_position = (Integer) holder.numberOfRoomsWanted.getTag();
        holder.numberOfRoomsWanted.setId(tag_position);

        holder.numberOfRoomsWanted.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                final int position2 = holder.numberOfRoomsWanted.getId();
                final EditText Caption = (EditText) holder.numberOfRoomsWanted;
                if (Caption.getText().toString().length() > 0) {
                    if(Integer.parseInt(holder.numberOfRoomsAvailable.getText().toString())<Integer.parseInt(Caption.getText().toString())){
                        Caption.setText(holder.numberOfRoomsAvailable.getText().toString());
                    }
                } else {
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        return convertView;
    }

    class ViewHolder {
        TextView roomType;
        TextView numberOfRoomsAvailable;
        EditText numberOfRoomsWanted;
    }

}


