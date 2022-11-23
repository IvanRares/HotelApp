package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelapp.access_objects.RoomDao;
import com.example.hotelapp.access_objects.RoomTypeDao;
import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tx1 = findViewById(R.id.textView);
                System.out.println("Here");
                AppDatabase db = androidx.room.Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"HotelDb.db").allowMainThreadQueries().build();
                System.out.println(getDatabasePath("HotelDb.db").getAbsolutePath());
                RoomDao roomDao = db.roomDao();
                Room room = roomDao.findByRoomName("1432");
                tx1.setText(room.getRoomId()+room.getRoomTypeId()+room.getRoomName()+room.isActive());
            }
        });
    }

}