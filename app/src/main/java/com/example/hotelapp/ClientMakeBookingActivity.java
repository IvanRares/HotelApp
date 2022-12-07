package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.utils.ListViewCheckbox;
import com.example.hotelapp.utils.ListViewEditTextAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
    AppDatabase db;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_make_booking);

        db = AppDatabase.getInstance(this);
        roomTypeList=db.roomTypeDao().getAllRoomTypes();
        amenityList=db.amenityDao().getAllAmenities();

        wantedRoomsListView = findViewById(R.id.client_make_booking_availableRoomsList);
        amenityListView=findViewById(R.id.client_make_booking_amenityList);
        listViewCheckboxAdapter=new ListViewCheckbox(this,amenityList);
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

        listViewEditTextAdapter=new ListViewEditTextAdapter(this,roomTypeList,dateButton.getText().toString(),endDateButton.getText().toString());
        wantedRoomsListView.setAdapter(listViewEditTextAdapter);
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

                listViewEditTextAdapter=new ListViewEditTextAdapter(context,roomTypeList,dateButton.getText().toString(),endDateButton.getText().toString());
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

                listViewEditTextAdapter=new ListViewEditTextAdapter(context,roomTypeList,dateButton.getText().toString(),endDateButton.getText().toString());
                wantedRoomsListView.setAdapter(listViewEditTextAdapter);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;


        cal.set(year + 1, month, day);
        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(context, style, endDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.set(year + 1, month, day + 1);
        endDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());


    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
    }
}