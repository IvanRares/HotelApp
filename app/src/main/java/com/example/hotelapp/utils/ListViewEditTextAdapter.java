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
import com.example.hotelapp.pojos.OfferAndPrices;
import com.example.hotelapp.pojos.PriceAndRoomTypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ListViewEditTextAdapter extends BaseAdapter {
    private Context context;
    private List<RoomType> list;
    private List<Integer> wantedRoomList;
    private String startDate;
    private String endDate;
    private AppDatabase db;
    private TextView totalPriceRooms;
    private OfferAndPrices offer;
    LayoutInflater mInflater;

    public ListViewEditTextAdapter(Context context, List<RoomType> list, String startDate, String endDate, TextView totalPriceRooms) {
        this.context = context;
        this.list = list;
        wantedRoomList = new ArrayList<>(Collections.nCopies(list.size(), 0));
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPriceRooms = totalPriceRooms;
        db = AppDatabase.getInstance(context);
    }

    public ListViewEditTextAdapter(Context context, List<RoomType> list, String startDate, String endDate, TextView totalPriceRooms, OfferAndPrices offer) {
        this.context = context;
        this.list = list;
        wantedRoomList = new ArrayList<>(Collections.nCopies(list.size(), 0));
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPriceRooms = totalPriceRooms;
        this.offer = offer;
        db = AppDatabase.getInstance(context);
    }

    public List<Integer> getWantedRoomList() {
        return wantedRoomList;
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

    public float calculatePrice() {
        float price = 0;
        List<PriceAndRoomTypes> priceList=new ArrayList<>();
        if (offer == null)
            priceList = db.priceDao().getAllPricesByDate(startDate, endDate);
        else{
            priceList=db.priceDao().getPriceAsListById(offer.price.getPriceId());
        }
        for (int i = 0; i < list.size(); i++) {
            for (PriceAndRoomTypes p : priceList) {
                try {
                    RoomType currentRoomType = list.get(i);
                    int wantedRooms = wantedRoomList.get(i);

                    Date buttonStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                    Date buttonEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                    Date priceStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(p.price.getStartDate());
                    Date priceEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(p.price.getEndDate());
                    if (currentRoomType.getRoomTypeName().equals(p.roomType.getRoomTypeName())) {
                        if (priceStartDate.after(buttonStartDate)) {
                            if (priceEndDate.after(buttonEndDate)) {
                                int days = (int) ChronoUnit.DAYS.between(priceStartDate.toInstant(), buttonEndDate.toInstant());
                                price += wantedRooms * p.price.getPriceValue() * days;
                            } else {
                                int days = (int) ChronoUnit.DAYS.between(priceStartDate.toInstant(), priceEndDate.toInstant());
                                price += wantedRooms * p.price.getPriceValue() * days;
                            }
                        } else {
                            if (priceEndDate.after(buttonEndDate)) {
                                int days = (int) ChronoUnit.DAYS.between(buttonStartDate.toInstant(), buttonEndDate.toInstant());
                                price += wantedRooms * p.price.getPriceValue() * days;
                            } else {
                                int days = (int) ChronoUnit.DAYS.between(buttonStartDate.toInstant(), priceEndDate.toInstant());
                                price += wantedRooms * p.price.getPriceValue() * days;
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return price;
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

            int availableRooms = db.roomTypeDao().getEmptyRoomsForRoomType(startDate, endDate, list.get(position).getRoomTypeId());
            holder.numberOfRoomsAvailable = (TextView) convertView.findViewById(R.id.list_view_adapter_emptyRooms);
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
                final EditText Caption = (EditText) holder.numberOfRoomsWanted;
                if (Caption.getText().toString().length() > 0) {
                    if (Integer.parseInt(holder.numberOfRoomsAvailable.getText().toString()) < Integer.parseInt(Caption.getText().toString())) {
                        Caption.setText(holder.numberOfRoomsAvailable.getText().toString());
                    }
                    wantedRoomList.set(position, Integer.parseInt(Caption.getText().toString()));
                    totalPriceRooms.setText(Float.toString(calculatePrice()));
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


