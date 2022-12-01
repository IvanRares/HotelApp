package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.pojos.UserAndUsertypes;

import java.util.Locale;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

    Button button;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = AppDatabase.getInstance(getApplicationContext());
        button = findViewById(R.id.loginOrRegisterButton);
        String loginType = getIntent().getExtras().getString("TYPE_OF_LOGIN");
        button.setText(loginType);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginType.toLowerCase(Locale.ROOT).equals("login"))
                    Login();
                else
                    Register();
            }
        });
    }

    private void Login() {
        EditText usernameInput = findViewById(R.id.editTextUsername);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        UserDao userDao = db.userDao();
        System.out.println(getApplicationContext().getDatabasePath("HotelDb.db"));
        Optional<UserAndUsertypes> foundValue = userDao.findByUsernameAndPassword(username, password);
        if(foundValue.isPresent()) {
            UserAndUsertypes user= foundValue.get();
            if (user.userType.getName().equals("Admin")) {
                Intent i = new Intent(this, AdminActivity.class);
                startActivity(i);
            }
        }
    }

    private void Register(){

    }
}