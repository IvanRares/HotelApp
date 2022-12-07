package com.example.hotelapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelapp.EditPriceActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentAdminPriceItemBinding;
import com.example.hotelapp.databinding.FragmentClientPriceItemBinding;
import com.example.hotelapp.pojos.PriceAndRoomTypes;

import java.util.ArrayList;
import java.util.List;

public class ClientPriceRecyclerViewAdapter extends RecyclerView.Adapter<ClientPriceRecyclerViewAdapter.ViewHolder>{
    private List<PriceAndRoomTypes> mValues = new ArrayList<>();
    private final Context mContext;

    public ClientPriceRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<PriceAndRoomTypes> newData) {
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

        return new ViewHolder(FragmentClientPriceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ClientPriceRecyclerViewAdapter.ViewHolder holder, int position) {
        int rowPos = holder.getBindingAdapterPosition();
        if (rowPos == 0) {
            setHeaderBg(holder.mStartDate);
            setHeaderBg(holder.mEndDate);
            setHeaderBg(holder.mPrice);
            setHeaderBg(holder.mRoomType);

            holder.mStartDate.setText("Start Date");
            holder.mEndDate.setText("End Date");
            holder.mPrice.setText("Price");
            holder.mRoomType.setText("Room Type");
        } else {
            setContentBg(holder.mStartDate);
            setContentBg(holder.mEndDate);
            setContentBg(holder.mPrice);
            setContentBg(holder.mRoomType);

            holder.mItem = mValues.get(position - 1);
            holder.mStartDate.setText(mValues.get(position - 1).price.getStartDate());
            holder.mEndDate.setText(mValues.get(position - 1).price.getEndDate());
            holder.mPrice.setText(Float.toString(mValues.get(position - 1).price.getPriceValue()));
            holder.mRoomType.setText(mValues.get(position - 1).roomType.getRoomTypeName());
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mStartDate;
        public final TextView mEndDate;
        public final TextView mPrice;
        public final TextView mRoomType;
        public PriceAndRoomTypes mItem;

        public ViewHolder(FragmentClientPriceItemBinding binding) {
            super(binding.getRoot());
            mStartDate = binding.clientPriceStartDate;
            mEndDate = binding.clientPriceEndDate;
            mPrice = binding.clientPricePrice;
            mRoomType = binding.clientPriceRoomType;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPrice.getText() + "'";
        }
    }
}
