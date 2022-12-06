package com.example.hotelapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.ImagesRoomTypeActivity;
import com.example.hotelapp.databinding.ClientRoomTypesListViewItemBinding;
import com.example.hotelapp.databinding.FragmentGuestRoomItemBinding;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.RoomTypeAndImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {
    private final HashMap<RoomTypeAndImage,String> roomTypeCountAndImage;
    private final List<RoomTypeAndImage> mValues;
    private final Context mContext;

    public RoomRecyclerViewAdapter(HashMap<RoomTypeAndImage,String> items,Context context) {
        roomTypeCountAndImage = items;
        Set keys = items.keySet();
        Iterator i = keys.iterator();
        mValues=items.keySet().stream().collect(Collectors.toList());
        mContext=context;
    }

    @Override
    public RoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RoomRecyclerViewAdapter.ViewHolder(FragmentGuestRoomItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final RoomRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mRoomTypeName.setText(mValues.get(position).roomType.getRoomTypeName());
        holder.mEmptyRooms.setText(roomTypeCountAndImage.get(mValues.get(position)));
        setImageViewWithByteArray(holder.mRoomTypeImage, mValues.get(position).image.getImageData());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRoomTypeName;
        public final TextView mEmptyRooms;
        public final ImageView mRoomTypeImage;
        public RoomTypeAndImage mItem;

        public ViewHolder(FragmentGuestRoomItemBinding binding) {
            super(binding.getRoot());
            mRoomTypeName = binding.fragmentGuestRoomItemRoomType;
            mEmptyRooms = binding.fragmentGuestRoomItemEmptyRooms;
            mRoomTypeImage = binding.fragmentGuestRoomItemImage;
        }

    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);}


}
