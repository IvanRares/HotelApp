package com.example.hotelapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.ImagesRoomTypeActivity;
import com.example.hotelapp.databinding.ClientRoomTypesListViewItemBinding;
import com.example.hotelapp.databinding.FragmentImageListViewItemBinding;
import com.example.hotelapp.entities.Image;
import com.example.hotelapp.pojos.RoomTypeAndImage;

import java.util.ArrayList;
import java.util.List;

public class RoomTypeImagesRecyclerViewAdapter extends RecyclerView.Adapter<RoomTypeImagesRecyclerViewAdapter.ViewHolder> {
    private final List<byte[]> mValues;
    private final Context mContext;

    public RoomTypeImagesRecyclerViewAdapter(List<byte[]> items,Context context) {
        mValues = items;
        mContext=context;
    }

    @Override
    public RoomTypeImagesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentImageListViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final RoomTypeImagesRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        setImageViewWithByteArray(holder.mRoomTypeImage, mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mRoomTypeImage;
        public byte[] mItem;

        public ViewHolder(FragmentImageListViewItemBinding binding) {
            super(binding.getRoot());
            mRoomTypeImage = binding.fragmentImageListViewItemImage;
        }

    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);}
}
