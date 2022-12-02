package com.example.hotelapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapp.EditRoomActivity;
import com.example.hotelapp.EditRoomTypeActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.hotelapp.databinding.FragmentAdminRoomTypeItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdminRoomTypesRecyclerViewAdapter extends RecyclerView.Adapter<AdminRoomTypesRecyclerViewAdapter.ViewHolder> {

    private List<RoomType> mValues=new ArrayList<>();
    private final Context mContext;

    public AdminRoomTypesRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RoomType> newData) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(newData);
            notifyDataSetChanged();
        }
        this.mValues = newData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAdminRoomTypeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    private void setHeaderBg(View view) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg);
    }

    private void setContentBg(View view) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int rowPos = holder.getBindingAdapterPosition();
        if (rowPos == 0) {
            setHeaderBg(holder.mRoomType);
            setHeaderBg(holder.mDescription);

            holder.mRoomType.setText("Room Type");
            holder.mDescription.setText("Description");
        } else {
            setContentBg(holder.mRoomType);
            setContentBg(holder.mDescription);

            holder.mItem = mValues.get(position - 1);
            holder.mRoomType.setText(mValues.get(position - 1).getRoomTypeName());
            holder.mDescription.setText(mValues.get(position - 1).getRoomTypeDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, EditRoomTypeActivity.class);
                    i.putExtra("roomTypeId", holder.mItem.getRoomTypeId());
                    mContext.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRoomType;
        public final TextView mDescription;
        public RoomType mItem;

        public ViewHolder(FragmentAdminRoomTypeItemBinding binding) {
            super(binding.getRoot());
            mRoomType = binding.adminRoomTypeRoomType;
            mDescription = binding.adminRoomTypeRoomTypeDescription;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}