package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.utils.InputFilterMin;

public class EditAmenityActivity extends AppCompatActivity {

    EditText amenityName;
    EditText price;
    Button saveButton;
    Button deleteButton;
    Amenity item;
    AppDatabase db;
    String option;
    int amenityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_amenity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Amenity");
        actionBar.setDisplayHomeAsUpEnabled(true);
        option = getIntent().getExtras().getString("Option");
        db = AppDatabase.getInstance(getApplicationContext());
        amenityName = findViewById(R.id.edit_amenity_amenityName);
        price = findViewById(R.id.edit_amenity_price);
        price.setFilters(new InputFilter[]{new InputFilterMin(0)});
        saveButton = findViewById(R.id.edit_amenity_saveButton);
        deleteButton = findViewById(R.id.edit_amenity_deleteButton);

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
            amenityId = getIntent().getExtras().getInt("amenityId");
            item = db.amenityDao().getAmenityById(amenityId);
            amenityName.setText(item.getAmenityName());
            price.setText(Float.toString(item.getPrice()));
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void Save() {
        if (!isEmpty()) {
            if (option.equals("Edit")) {
                item.setAmenityName(amenityName.getText().toString());
                item.setPrice(Float.parseFloat(price.getText().toString()));
                db.amenityDao().updateAmenity(item);
            } else {
                String name = amenityName.getText().toString();
                float amenityPrice = Float.parseFloat(price.getText().toString());
                System.out.println(name + " " + Float.toString(amenityPrice));
                db.amenityDao().insertAmenity(new Amenity(name, amenityPrice));
            }
            finish();
        }
    }

    private void Delete() {
        item.setActive(false);
        db.amenityDao().updateAmenity(item);
        finish();
    }

    private boolean isEmpty() {
        return (amenityName.getText().toString().isEmpty() || price.getText().toString().isEmpty());
    }
}