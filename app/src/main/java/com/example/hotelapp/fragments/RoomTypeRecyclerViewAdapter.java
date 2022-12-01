package com.example.hotelapp.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelapp.databinding.ClientRoomTypesListViewItemBinding;
import com.example.hotelapp.databinding.FragmentAdminRoomItemBinding;
import com.example.hotelapp.databinding.FragmentAdminRoomItemListBinding;
import com.example.hotelapp.databinding.FragmentRoomTypesBinding;
import com.example.hotelapp.pojos.RoomAndRoomTypes;
import com.example.hotelapp.pojos.RoomTypeAndImage;

import java.util.List;

public class RoomTypeRecyclerViewAdapter extends RecyclerView.Adapter<RoomTypeRecyclerViewAdapter.ViewHolder> {

    private final List<RoomTypeAndImage> mValues;

    public RoomTypeRecyclerViewAdapter(List<RoomTypeAndImage> items) {
        mValues = items;
    }

    @Override
    public RoomTypeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ClientRoomTypesListViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final RoomTypeRecyclerViewAdapter.ViewHolder holder, int position) {

            holder.mItem = mValues.get(position);
            holder.mRoomTypeName.setText(mValues.get(position).roomType.getRoomTypeName());
            holder.mRoomTypeDescription.setText(mValues.get(position).roomType.getRoomTypeDescription());
            setImageViewWithByteArray(holder.mRoomTypeImage, mValues.get(position).image.getImageData());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRoomTypeName;
        public final TextView mRoomTypeDescription;
        public final ImageView mRoomTypeImage;
        public RoomTypeAndImage mItem;

        public ViewHolder(ClientRoomTypesListViewItemBinding binding) {
            super(binding.getRoot());
            mRoomTypeName = binding.clientRoomTypesListViewItemRoomTypeName;
            mRoomTypeDescription = binding.clientRoomTypesListViewItemRoomTypeDescription;
            mRoomTypeImage = binding.clientRoomTypesListViewItemRoomTypeImage;
        }

    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);}




}
