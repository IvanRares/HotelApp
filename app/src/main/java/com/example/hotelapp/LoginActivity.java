package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.User;

public class LoginActivity extends AppCompatActivity {

    Button button;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=AppDatabase.getInstance(getApplicationContext());
        button = findViewById(R.id.loginOrRegisterButton);
        button.setText(getIntent().getExtras().getString("TYPE_OF_LOGIN"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginOrRegister();
            }
        });
    }

    private void LoginOrRegister() {
        EditText usernameInput = findViewById(R.id.editTextUsername);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        UserDao userDao = db.userDao();
        User user = userDao.findByUsernameAndPassword(username, password);
        Toast toast=Toast.makeText(getApplicationContext(),user.getUsername()+ user.getPassword(),Toast.LENGTH_SHORT);
        toast.show();
    }
}