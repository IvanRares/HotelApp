package com.example.hotelapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapp.EditAmenityActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentAdminAmenityItemBinding;
import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.fragments.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdminAmenityRecyclerViewAdapter extends RecyclerView.Adapter<AdminAmenityRecyclerViewAdapter.ViewHolder> {

    private List<Amenity> mValues = new ArrayList<>();
    private final Context mContext;

    public AdminAmenityRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Amenity> newData) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(newData);
            notifyDataSetChanged();
        }
        this.mValues = newData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAdminAmenityItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

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
            setHeaderBg(holder.mAmenityName);
            setHeaderBg(holder.mPrice);

            holder.mAmenityName.setText("Amenity Name");
            holder.mPrice.setText("Price");
        } else {
            setContentBg(holder.mAmenityName);
            setContentBg(holder.mPrice);

            holder.mItem = mValues.get(position - 1);
            holder.mAmenityName.setText(mValues.get(position - 1).getAmenityName());
            holder.mPrice.setText(Float.toString(mValues.get(position - 1).getPrice()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, EditAmenityActivity.class);
                    i.putExtra("Option", "Edit");
                    i.putExtra("amenityId", holder.mItem.getAmenityId());
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
        public final TextView mAmenityName;
        public final TextView mPrice;
        public Amenity mItem;

        public ViewHolder(FragmentAdminAmenityItemBinding binding) {
            super(binding.getRoot());
            mAmenityName = binding.adminAmenityName;
            mPrice = binding.adminAmenityPrice;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPrice.getText() + "'";
        }
    }
}