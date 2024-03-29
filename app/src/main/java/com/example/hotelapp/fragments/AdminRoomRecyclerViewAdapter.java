package com.example.hotelapp.fragments;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapp.EditRoomActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentAdminRoomItemBinding;
import com.example.hotelapp.pojos.RoomAndRoomTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RoomAndRoomTypes}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdminRoomRecyclerViewAdapter extends RecyclerView.Adapter<AdminRoomRecyclerViewAdapter.ViewHolder> {

    private List<RoomAndRoomTypes> mValues=new ArrayList<>();
    private final Context mContext;

    public AdminRoomRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RoomAndRoomTypes> newData) {
        if(mValues!=null)
        {
            mValues.clear();
            mValues.addAll(newData);
            notifyDataSetChanged();
        }
        this.mValues = newData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAdminRoomItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

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
            setHeaderBg(holder.mRoomName);
            setHeaderBg(holder.mContentView);

            holder.mRoomName.setText("Room Name");
            holder.mContentView.setText("Room Type");
        } else {
            setContentBg(holder.mRoomName);
            setContentBg(holder.mContentView);

            holder.mItem = mValues.get(position-1);
            holder.mRoomName.setText(mValues.get(position - 1).room.getRoomName());
            holder.mContentView.setText(mValues.get(position - 1).roomType.getRoomTypeName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mContext, EditRoomActivity.class);
                    i.putExtra("Option","Edit");
                    i.putExtra("roomId",holder.mItem.room.getRoomId());
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
        public final TextView mRoomName;
        public final TextView mContentView;
        public RoomAndRoomTypes mItem;

        public ViewHolder(FragmentAdminRoomItemBinding binding) {
            super(binding.getRoot());
            mRoomName = binding.adminRoomRoomName;
            mContentView = binding.adminRoomRoomType;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}