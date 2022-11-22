package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.hotelapp.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * from Users WHERE Username Like:username AND Password LIKE:password LIMIT 1")
    User findByUsernameAndPassword(String username,String password);
}
