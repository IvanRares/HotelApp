package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.State;
import com.example.hotelapp.pojos.BookingsAndUsers;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeBookingActivity extends AppCompatActivity {

    TextView username;
    TextView startDate;
    TextView endDate;
    TextView totalPrice;
    Spinner stateSpinner;
    Button update;
    AppDatabase db;
    List<State> stateList;
    BookingsAndUsers item;
    int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_employee_booking);
        username=findViewById(R.id.employee_booking_usernameData);
        startDate=findViewById(R.id.employee_booking_startDateData);
        endDate=findViewById(R.id.employee_booking_endDateData);
        totalPrice=findViewById(R.id.employee_booking_totalPriceData);
        stateSpinner=findViewById(R.id.employee_booking_stateData);
        update=findViewById(R.id.employee_booking_updateButton);
        db=AppDatabase.getInstance(getApplicationContext());
        stateList=db.stateDao().getStates();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, stateList.stream().map(State::getStateName).collect(Collectors.toList()));
        stateSpinner.setAdapter(adapter);
        bookingId=getIntent().getExtras().getInt("bookingId");
        item=db.bookingDao().getBookingById(bookingId);

        username.setText(item.user.getUsername());
        startDate.setText(item.booking.getStartDate());
        endDate.setText(item.booking.getEndDate());
        totalPrice.setText(Float.toString(item.booking.getTotalPrice()));
        int pos = adapter.getPosition(item.state.getStateName());
        stateSpinner.setSelection(pos);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void Update(){
        String selectedState = stateSpinner.getSelectedItem().toString();
        int roomTypeId = stateList.stream().filter(x -> x.getStateName().equals(selectedState)).findFirst().get().getStateId();
        item.booking.setStateId(roomTypeId);
        db.bookingDao().updateBooking(item.booking);
        finish();
    }
}