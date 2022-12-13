package com.example.hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.pojos.UserAndUsertypes;

import java.util.Locale;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

    Button button;
    AppDatabase db;
    EditText usernameInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        db = AppDatabase.getInstance(getApplicationContext());
        button = findViewById(R.id.loginOrRegisterButton);
        usernameInput = findViewById(R.id.editTextUsername);
        passwordInput = findViewById(R.id.editTextTextPassword);
        String loginType = getIntent().getExtras().getString("TYPE_OF_LOGIN");
        if(loginType.toLowerCase(Locale.ROOT).equals("login"))
            actionBar.setTitle("Login");
        else
            actionBar.setTitle("Register");
        button.setText(loginType);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginType.toLowerCase(Locale.ROOT).equals("login")) {
                    Login();
                    actionBar.setTitle("Login");
                }

                else {
                    Register();
                    actionBar.setTitle("Register");
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
        return true;
    }

    private void Login() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        UserDao userDao = db.userDao();
        System.out.println(getApplicationContext().getDatabasePath("HotelDb.db"));
        Optional<UserAndUsertypes> foundValue = userDao.findByUsernameAndPassword(username, password);
        if (foundValue.isPresent() && !isEmpty()) {
            UserAndUsertypes user = foundValue.get();
            switch (user.userType.getName()) {
                case "Admin":
                    Intent i = new Intent(this, AdminActivity.class);
                    startActivity(i);
                    break;
                case "Employee":
                    i = new Intent(this, EmployeeActivity.class);
                    startActivity(i);
                    break;
                case "Client":
                    i = new Intent(this, ClientActivity.class);
                    i.putExtra("userId", user.user.getUserId());
                    startActivity(i);
                    break;
                default:
                    break;
            }
            finish();
        }
    }

    private void Register() {
        if (!isEmpty()) {
            boolean success = false;
            try {
                db.userDao().insertUser(new User(3, usernameInput.getText().toString(), passwordInput.getText().toString()));
                success = true;
            } catch (SQLiteException e) {
                Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
            }
            if (success) {
                Intent i = new Intent(this, ClientActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    private boolean isEmpty() {
        return usernameInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty();
    }
}