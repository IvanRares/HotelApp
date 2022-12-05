package com.example.hotelapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelapp.EditImageActivity;
import com.example.hotelapp.EditRoomActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentAdminImageItemBinding;
import com.example.hotelapp.entities.Image;
import com.example.hotelapp.fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.hotelapp.databinding.FragmentAdminImageItemBinding;
import com.example.hotelapp.pojos.ImageAndRoomType;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdminImageRecyclerViewAdapter extends RecyclerView.Adapter<AdminImageRecyclerViewAdapter.ViewHolder> {

    private List<ImageAndRoomType> mValues = new ArrayList<>();
    private final Context mContext;

    public AdminImageRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ImageAndRoomType> newData) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(newData);
            notifyDataSetChanged();
        }
        this.mValues = newData;
    }

    private void setHeaderBg(View view) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg);
    }

    private void setContentBg(View view) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAdminImageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int rowPos = holder.getBindingAdapterPosition();
        if (rowPos == 0) {
            setHeaderBg(holder.mImageName);
            setHeaderBg(holder.mImage);
            setHeaderBg(holder.mImageData);
            setHeaderBg(holder.mImageRoomType);

            holder.mImageName.setText("Image Name");
            holder.mImageRoomType.setText("Room Type");
            holder.mImage.setText("Image");
        } else {
            setContentBg(holder.mImageName);
            setContentBg(holder.mImage);
            setContentBg(holder.mImageData);
            setContentBg(holder.mImageRoomType);

            holder.mItem = mValues.get(position - 1);
            holder.mImageName.setText(mValues.get(position - 1).image.getImageName());
            holder.mImageRoomType.setText(mValues.get(position - 1).roomType.getRoomTypeName());
            setImageViewWithByteArray(holder.mImageData, mValues.get(position - 1).image.getImageData());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, EditImageActivity.class);
                    i.putExtra("Option", "Edit");
                    i.putExtra("imageId", holder.mItem.image.getImageId());
                    mContext.startActivity(i);
                }
            });
        }
    }

    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        view.bringToFront();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }


    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mImageName;
        public final TextView mImage;
        public final ImageView mImageData;
        public final TextView mImageRoomType;
        public ImageAndRoomType mItem;

        public ViewHolder(FragmentAdminImageItemBinding binding) {
            super(binding.getRoot());
            mImage = binding.adminImageImage;
            mImageName = binding.adminImageName;
            mImageData = binding.adminImageData;
            mImageRoomType = binding.adminImageRoomType;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mImageName.getText() + "'";
        }
    }
}