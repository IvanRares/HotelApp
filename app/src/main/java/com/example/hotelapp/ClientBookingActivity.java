package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hotelapp.entities.State;
import com.example.hotelapp.pojos.BookingsAndUsers;

import java.util.List;
import java.util.stream.Collectors;

public class ClientBookingActivity extends AppCompatActivity {

    TextView username;
    TextView startDate;
    TextView endDate;
    TextView totalPrice;
    Button update;
    AppDatabase db;
    BookingsAndUsers item;
    int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Booking");
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_client_booking);
        username=findViewById(R.id.client_booking_usernameData);
        startDate=findViewById(R.id.client_booking_startDateData);
        endDate=findViewById(R.id.client_booking_endDateData);
        totalPrice=findViewById(R.id.client_booking_totalPriceData);
        update=findViewById(R.id.client_booking_updateButton);
        db=AppDatabase.getInstance(getApplicationContext());
        bookingId=getIntent().getExtras().getInt("bookingId");
        item=db.bookingDao().getBookingById(bookingId);
        username.setText(item.user.getUsername());
        startDate.setText(item.booking.getStartDate());
        endDate.setText(item.booking.getEndDate());
        totalPrice.setText(Float.toString(item.booking.getTotalPrice()));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBooking();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void cancelBooking(){
        item.booking.setStateId(2);
        db.bookingDao().updateBooking(item.booking);
        finish();
    }
}