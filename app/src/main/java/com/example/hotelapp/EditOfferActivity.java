package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hotelapp.entities.Offer;
import com.example.hotelapp.entities.Price;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.OfferAndPrices;
import com.example.hotelapp.pojos.PriceAndRoomTypes;
import com.example.hotelapp.utils.InputFilterMin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EditOfferActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog endDatePickerDialog;
    private Button endDateButton;
    EditText offerName;
    EditText price;
    Spinner roomTypes;
    List<RoomType> roomTypeList;
    Button saveButton;
    Button deleteButton;
    OfferAndPrices item;
    AppDatabase db;
    String option;
    int priceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);
        initDatePicker(this);
        dateButton = findViewById(R.id.edit_offer_datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });
        endDateButton = findViewById(R.id.edit_offer_endDatePickerButton);
        endDateButton.setText(getNextDayDate());
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndDatePicker(v);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Offers");
        actionBar.setDisplayHomeAsUpEnabled(true);

        option = getIntent().getExtras().getString("Option");
        db = AppDatabase.getInstance(getApplicationContext());
        roomTypeList = db.roomTypeDao().getAllRoomTypes();
        offerName = findViewById(R.id.edit_offer_offerName);
        price = findViewById(R.id.edit_offer_price);
        price.setFilters(new InputFilter[]{new InputFilterMin(0)});
        saveButton = findViewById(R.id.edit_offer_saveButton);
        deleteButton = findViewById(R.id.edit_offer_deleteButton);
        roomTypes = findViewById(R.id.edit_offer_roomTypes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, roomTypeList.stream().map(RoomType::getRoomTypeName).collect(Collectors.toList()));
        roomTypes.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });
        if (option.equals("Edit")) {
            priceId = getIntent().getExtras().getInt("priceId");
            item = db.offerDao().getOfferById(priceId);
            int pos = adapter.getPosition(item.roomType.getRoomTypeName());
            roomTypes.setSelection(pos);
            offerName.setText(item.offer.getOfferName());
            dateButton.setText(item.price.getStartDate());
            endDateButton.setText(item.price.getEndDate());
            price.setText(Float.toString(item.price.getPriceValue()));
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private boolean isEmpty() {
        return (price.getText().toString().isEmpty() || offerName.getText().toString().isEmpty());
    }

    private void Save() {
        if (!isEmpty()) {
            if (option.equals("Edit")) {
                item.offer.setOfferName(offerName.getText().toString());
                item.price.setPriceValue(Float.parseFloat(price.getText().toString()));
                item.price.setStartDate(dateButton.getText().toString());
                item.price.setEndDate(endDateButton.getText().toString());
                String selectedRoomType = roomTypes.getSelectedItem().toString();
                int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
                item.price.setRoomTypeId(roomTypeId);
                db.priceDao().updatePrice(item.price);
                db.offerDao().updateOffer(item.offer);


            } else {
                String selectedRoomType = roomTypes.getSelectedItem().toString();
                int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
                int priceId=(int)db.priceDao().insertPrice(new Price(roomTypeId, dateButton.getText().toString(), endDateButton.getText().toString(), Float.parseFloat(price.getText().toString()), true));
                db.offerDao().insertOffer(new Offer(priceId,offerName.getText().toString()));
            }
            finish();
        }
    }

    private void Delete() {
        item.price.setActive(false);
        db.priceDao().updatePrice(item.price);
        item.offer.setActive(false);
        db.offerDao().updateOffer(item.offer);
        finish();
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
        cal.set(year + 1, month, day+1);
        endDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
    }
}