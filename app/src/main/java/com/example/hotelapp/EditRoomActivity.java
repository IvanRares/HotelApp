package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.RoomAndRoomTypes;

import java.util.List;
import java.util.stream.Collectors;

public class EditRoomActivity extends AppCompatActivity {

    EditText roomName;
    Spinner roomTypes;
    Button saveButton;
    RoomAndRoomTypes item;
    List<RoomType> roomTypeList;
    AppDatabase db;
    String option;
    int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        option = getIntent().getExtras().getString("Option");
        db = AppDatabase.getInstance(getApplicationContext());
        roomTypeList = db.roomTypeDao().getAllRoomTypes();
        roomName = findViewById(R.id.edit_room_roomName);
        roomTypes = findViewById(R.id.edit_room_roomTypes);
        saveButton = findViewById(R.id.edit_room_saveButton);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, roomTypeList.stream().map(RoomType::getRoomTypeName).collect(Collectors.toList()));
        roomTypes.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
        if (option.equals("Edit")) {
            roomId = getIntent().getExtras().getInt("roomId");
            item = db.roomDao().getRoomById(roomId);
            roomName.setText(item.room.getRoomName());
            int pos = adapter.getPosition(item.roomType.getRoomTypeName());
            roomTypes.setSelection(pos);
        }
    }

    private void Save() {
        if(option.equals("Edit")) {
            item.room.setRoomName(roomName.getText().toString());
            String selectedRoomType = roomTypes.getSelectedItem().toString();
            int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
            item.room.setRoomTypeId(roomTypeId);
            db.roomDao().updateRoom(item.room);
        }
        else{
            String selectedRoomType = roomTypes.getSelectedItem().toString();
            int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
            long id=db.roomDao().insertRoom(new Room(roomTypeId, roomName.getText().toString()));
//            db.roomDao().insertRoom();
            Room item=db.roomDao().getRoomByRoomId(id);
            System.out.println(item.getRoomName()+" "+item.isActive());
        }
        finish();
    }
}