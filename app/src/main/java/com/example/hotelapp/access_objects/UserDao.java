package com.example.hotelapp.access_objects;

import androidx.core.util.Pair;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserAndUsertypes;
import com.example.hotelapp.entities.UserType;

import java.util.Map;

@Dao
public interface UserDao {

    @Transaction
    @Query("SELECT * from Users WHERE Username Like:username AND Password LIKE:password LIMIT 1")
    UserAndUsertypes findByUsernameAndPassword(String username, String password);
}
