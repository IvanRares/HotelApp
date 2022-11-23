package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelapp.access_objects.UserDao;
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
                AppDatabase db = DatabaseCopier.getInstance(getApplicationContext()).getRoomDatabase();
                System.out.println(getDatabasePath("HotelDb.db").getAbsolutePath());
                UserDao userDao = db.userDao();
                User user = userDao.findByUsernameAndPassword("rares", "rares");
                tx1.setText(user.getUserId() + user.getTypeId() + user.getUsername() + user.getPassword());
            }
        });
    }

}