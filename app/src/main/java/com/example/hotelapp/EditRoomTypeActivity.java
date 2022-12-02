package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hotelapp.entities.RoomType;

public class EditRoomTypeActivity extends AppCompatActivity {

    EditText description;
    Button saveButton;
    RoomType item;
    int roomTypeId;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_type);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        db = AppDatabase.getInstance(getApplicationContext());
        description=findViewById(R.id.edit_room_type_description);
        saveButton=findViewById(R.id.edit_room_type_saveButton);
        roomTypeId=getIntent().getExtras().getInt("roomTypeId");
        item=db.roomTypeDao().getRoomType(roomTypeId);
        description.setText(item.getRoomTypeDescription());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void Save(){
        if(!isEmpty()) {
            item.setRoomTypeDescription(description.getText().toString());
            db.roomTypeDao().updateRoomType(item);
            finish();
        }
    }

    private boolean isEmpty(){
        return (description.getText().toString().isEmpty());
    }
}