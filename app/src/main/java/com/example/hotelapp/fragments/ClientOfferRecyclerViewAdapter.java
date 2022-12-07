package com.example.hotelapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelapp.EditOfferActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentAdminOfferItemBinding;
import com.example.hotelapp.databinding.FragmentClientOfferItemBinding;
import com.example.hotelapp.databinding.FragmentClientOffersBinding;
import com.example.hotelapp.pojos.OfferAndPrices;

import java.util.ArrayList;
import java.util.List;

public class ClientOfferRecyclerViewAdapter extends RecyclerView.Adapter<ClientOfferRecyclerViewAdapter.ViewHolder>{
    private List<OfferAndPrices> mValues = new ArrayList<>();
    private final Context mContext;

    public ClientOfferRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<OfferAndPrices> newData) {
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

        return new ClientOfferRecyclerViewAdapter.ViewHolder(FragmentClientOfferItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int rowPos = holder.getBindingAdapterPosition();
        if (rowPos == 0) {
            setHeaderBg(holder.mOfferName);
            setHeaderBg(holder.mStartDate);
            setHeaderBg(holder.mEndDate);
            setHeaderBg(holder.mPrice);
            setHeaderBg(holder.mRoomType);

            holder.mOfferName.setText("Name");
            holder.mStartDate.setText("Start Date");
            holder.mEndDate.setText("End Date");
            holder.mPrice.setText("Price");
            holder.mRoomType.setText("Room Type");
        } else {
            setContentBg(holder.mOfferName);
            setContentBg(holder.mStartDate);
            setContentBg(holder.mEndDate);
            setContentBg(holder.mPrice);
            setContentBg(holder.mRoomType);

            holder.mItem = mValues.get(position - 1);
            holder.mOfferName.setText(mValues.get(position - 1).offer.getOfferName());
            holder.mStartDate.setText(mValues.get(position - 1).price.getStartDate());
            holder.mEndDate.setText(mValues.get(position - 1).price.getEndDate());
            holder.mPrice.setText(Float.toString(mValues.get(position - 1).price.getPriceValue()));
            holder.mRoomType.setText(mValues.get(position - 1).roomType.getRoomTypeName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, EditOfferActivity.class);
                    i.putExtra("Option", "Edit");
                    i.putExtra("priceId", holder.mItem.price.getPriceId());
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
        public final TextView mOfferName;
        public final TextView mStartDate;
        public final TextView mEndDate;
        public final TextView mPrice;
        public final TextView mRoomType;
        public OfferAndPrices mItem;

        public ViewHolder(FragmentClientOfferItemBinding binding) {
            super(binding.getRoot());
            mOfferName = binding.clientOfferName;
            mStartDate = binding.clientOfferStartDate;
            mEndDate = binding.clientOfferEndDate;
            mPrice = binding.clientOfferPrice;
            mRoomType = binding.clientOfferRoomType;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mOfferName.getText() + "'";
        }
    }
}
