package com.example.hotelapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.ClientBookingActivity;
import com.example.hotelapp.EmployeeBookingActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.databinding.FragmentClientBookingItemBinding;
import com.example.hotelapp.databinding.FragmentEmployeeBookingItemBinding;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.hotelapp.databinding.FragmentClientBookingItemBinding;
import com.example.hotelapp.pojos.BookingsAndUsers;
import com.example.hotelapp.utils.ListViewFitHeight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ClientBookingRecyclerViewAdapter extends RecyclerView.Adapter<ClientBookingRecyclerViewAdapter.ViewHolder> {

    private List<BookingsAndUsers> mValues = new ArrayList<>();
    private final Context mContext;
    private final int mUserId;
    AppDatabase db;

    public ClientBookingRecyclerViewAdapter(Context context, int userId) {
        mContext = context;
        mUserId = userId;
        db = AppDatabase.getInstance(context);
    }

    public void setData(List<BookingsAndUsers> newData) {
        if (mValues != null) {
            mValues.clear();
            mValues.addAll(newData);
            notifyDataSetChanged();
        }
        this.mValues = newData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentClientBookingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    private void setHeaderBg(View view) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg);
    }

    private void setContentBg(View view) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }

    @Override
    public void onBindViewHolder(final ClientBookingRecyclerViewAdapter.ViewHolder holder, int position) {
        int rowPos = holder.getBindingAdapterPosition();
        if (rowPos == 0) {
            setHeaderBg(holder.mUsername);
            setHeaderBg(holder.mStartDate);
            setHeaderBg(holder.mEndDate);
            setHeaderBg(holder.mNumberOfRooms);
            setHeaderBg(holder.mRoomList);
            setHeaderBg(holder.mTotalPrice);
            setHeaderBg(holder.mState);

            holder.mUsername.setText("Username");
            holder.mStartDate.setText("Start Date");
            holder.mEndDate.setText("End Date");
            holder.mNumberOfRooms.setText("Number of Rooms");
            List<String> headerList = new ArrayList<>();
            headerList.add("Booked Rooms");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, headerList);
            holder.mRoomList.setAdapter(adapter);
            holder.mTotalPrice.setText("Total Price");
            holder.mState.setText("State");
        } else {
            setContentBg(holder.mUsername);
            setContentBg(holder.mStartDate);
            setContentBg(holder.mEndDate);
            setContentBg(holder.mNumberOfRooms);
            setContentBg(holder.mRoomList);
            setContentBg(holder.mTotalPrice);
            setContentBg(holder.mState);

            holder.mItem = mValues.get(position - 1);
            holder.mUsername.setText(holder.mItem.user.getUsername());
            holder.mStartDate.setText(holder.mItem.booking.getStartDate());
            holder.mEndDate.setText(holder.mItem.booking.getEndDate());
            holder.mNumberOfRooms.setText(Integer.toString(holder.mItem.booking.getNumberOfRooms()));
            List<Room> bookedRooms = db.bookingDao().getRoomsByBooking(holder.mItem.booking.getBookingId());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, bookedRooms.stream().map(Room::getRoomName).collect(Collectors.toList()));
            holder.mRoomList.setAdapter(adapter);
            holder.mTotalPrice.setText(Float.toString(holder.mItem.booking.getTotalPrice()));
            holder.mState.setText(holder.mItem.state.getStateName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String format = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                        Date date = sdf.parse(holder.mItem.booking.getStartDate());
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -1);
                        Date today = cal.getTime();
                        if (today.before(date) && holder.mItem.state.getStateName().equals("Active")) {
                            Intent i = new Intent(mContext, ClientBookingActivity.class);
                            i.putExtra("bookingId", holder.mItem.booking.getBookingId());
                            mContext.startActivity(i);
                        } else {
                            if (!holder.mItem.state.getStateName().equals("Active")) {
                                Toast toast = Toast.makeText(mContext, "Booking is " + holder.mItem.state.getStateName(), Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                Toast toast = Toast.makeText(mContext, "Start date already passed!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mUsername;
        public final TextView mStartDate;
        public final TextView mEndDate;
        public final TextView mNumberOfRooms;
        public final ListViewFitHeight mRoomList;
        public final TextView mTotalPrice;
        public final TextView mState;
        public BookingsAndUsers mItem;

        public ViewHolder(FragmentClientBookingItemBinding binding) {
            super(binding.getRoot());
            mUsername = binding.clientBookingUsername;
            mStartDate = binding.clientBookingStartDate;
            mEndDate = binding.clientBookingEndDate;
            mNumberOfRooms = binding.clientBookingNumberOfRooms;
            mRoomList = binding.clientBookingRoomList;
            mTotalPrice = binding.clientBookingTotalPrice;
            mState = binding.clientBookingState;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUsername.getText() + "'";
        }
    }
}