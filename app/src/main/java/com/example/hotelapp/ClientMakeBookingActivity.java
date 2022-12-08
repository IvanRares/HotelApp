package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.entities.AmenityBooking;
import com.example.hotelapp.entities.Booking;
import com.example.hotelapp.entities.BookingRoom;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.PriceAndRoomTypes;
import com.example.hotelapp.utils.ListViewCheckbox;
import com.example.hotelapp.utils.ListViewEditTextAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ClientMakeBookingActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog endDatePickerDialog;
    private Button endDateButton;
    ListView wantedRoomsListView;
    ListViewEditTextAdapter listViewEditTextAdapter;
    ListView amenityListView;
    ListViewCheckbox listViewCheckboxAdapter;
    List<RoomType> roomTypeList;
    List<Amenity> amenityList;
    TextView totalPrice;
    TextView totalPriceRooms;
    TextView totalPriceAmenities;
    Button makeBooking;
    AppDatabase db;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_make_booking);

        db = AppDatabase.getInstance(this);
        roomTypeList = db.roomTypeDao().getAllRoomTypes();
        amenityList = db.amenityDao().getAllAmenities();

        totalPrice = findViewById(R.id.client_make_booking_totalPrice);
        totalPriceRooms = findViewById(R.id.client_make_booking_totalPriceRooms);
        totalPriceRooms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatePrice();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        totalPriceAmenities = findViewById(R.id.client_make_booking_totalPriceAmenities);
        totalPriceAmenities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatePrice();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        userId = getIntent().getExtras().getInt("userId");
        makeBooking = findViewById(R.id.client_make_booking_makeBooking);
        makeBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBooking();
            }
        });

        wantedRoomsListView = findViewById(R.id.client_make_booking_availableRoomsList);
        amenityListView = findViewById(R.id.client_make_booking_amenityList);
        listViewCheckboxAdapter = new ListViewCheckbox(this, amenityList, totalPriceAmenities);
        amenityListView.setAdapter(listViewCheckboxAdapter);

        initDatePicker(this);
        dateButton = findViewById(R.id.client_make_booking_datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });
        endDateButton = findViewById(R.id.client_make_booking_endDatePickerButton);
        endDateButton.setText(getNextDayDate());
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndDatePicker(v);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listViewEditTextAdapter = new ListViewEditTextAdapter(this, roomTypeList, dateButton.getText().toString(), endDateButton.getText().toString(), totalPriceRooms);
        wantedRoomsListView.setAdapter(listViewEditTextAdapter);
    }

    private void createBooking() {
        String startDate=dateButton.getText().toString();
        String endDate=dateButton.getText().toString();
        List<Integer> wantedRoomList = listViewEditTextAdapter.getWantedRoomList();
        List<Boolean> isCheckedList = listViewCheckboxAdapter.getIsCheckedList();
        int numberOfRooms=wantedRoomList.stream().mapToInt(Integer::intValue).sum();
        int bookingId = (int) db.bookingDao().insertBooking(new Booking(userId, startDate, endDate, numberOfRooms,Float.parseFloat(totalPrice.getText().toString())));
        List<Room> availableRoomsByType=new ArrayList<>();
        for (int i=0;i<roomTypeList.size();i++) {
            if(wantedRoomList.get(i)>0) {
                availableRoomsByType.clear();
                availableRoomsByType.addAll(db.roomDao().getEmptyRoomsForRoomType(startDate, endDate, roomTypeList.get(i).getRoomTypeId()));
                Collections.shuffle(availableRoomsByType);
                for (Room r:availableRoomsByType.stream().limit(wantedRoomList.get(i)).collect(Collectors.toList())) {
                    db.bookingDao().insertBookingRoom(new BookingRoom(bookingId,r.getRoomId()));
                }
            }
        }
        for(int i=0;i<amenityList.size();i++){
            if(isCheckedList.get(i))
                db.bookingDao().insertAmenityBooking(new AmenityBooking(amenityList.get(i).getAmenityId(),bookingId));
        }
        finish();
    }

    private void updatePrice() {
        float roomsPrice = Float.parseFloat(totalPriceRooms.getText().toString());
        float amenitiesPrice = Float.parseFloat(totalPriceAmenities.getText().toString());
        if (roomsPrice == 0)
            totalPrice.setText("0");
        else
            totalPrice.setText(Float.toString(roomsPrice + amenitiesPrice));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    private String getNextDayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    private void initDatePicker(Context context) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);

                dateButton.setText(sdf.format(calendar.getTime()));
                calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                endDateButton.setText(sdf.format(calendar.getTime()));
                endDatePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

                listViewEditTextAdapter = new ListViewEditTextAdapter(context, roomTypeList, dateButton.getText().toString(), endDateButton.getText().toString(), totalPriceRooms);
                wantedRoomsListView.setAdapter(listViewEditTextAdapter);

            }
        };
        DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);

                endDateButton.setText(sdf.format(calendar.getTime()));

                listViewEditTextAdapter = new ListViewEditTextAdapter(context, roomTypeList, dateButton.getText().toString(), endDateButton.getText().toString(), totalPriceRooms);
                wantedRoomsListView.setAdapter(listViewEditTextAdapter);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;


        cal.set(year, month, day);
        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(context, style, endDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.set(year, month, day + 1);
        endDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
    }
}