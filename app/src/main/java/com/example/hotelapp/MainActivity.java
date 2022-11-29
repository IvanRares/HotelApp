package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelapp.access_objects.RoomDao;
import com.example.hotelapp.access_objects.RoomTypeDao;
import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.User;

public class MainActivity extends AppCompatActivity {


    Button loginButton;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.login);
        registerButton=findViewById(R.id.register);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin("Login");
//                TextView tx1 = findViewById(R.id.textView);
//                AppDatabase db = androidx.room.Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"HotelDb.db").allowMainThreadQueries().build();
//                //AppDatabase db = databaseCopier.getDatabase();
//                System.out.println(getDatabasePath("HotelDb.db").getAbsolutePath());
//                UserDao userDao = db.userDao();
//                User user=userDao.findByUsernameAndPassword("rares","rares");
//                System.out.println(user.getUsername());
//                RoomDao roomDao = db.roomDao();
//                Room room = roomDao.findByRoomName();
//                System.out.println(room);
//                tx1.setText(room.getRoomId()+room.getRoomTypeId()+room.getRoomName()+room.isActive());
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin("Register");
            }
        });
    }

    private void switchToLogin(String typeOfLogin) {
        Intent i=new Intent(this,LoginActivity.class);
        i.putExtra("TYPE_OF_LOGIN",typeOfLogin);
        startActivity(i);
    }

}