package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.hotelapp.entities.User;
import com.example.hotelapp.pojos.UserAndUsertypes;

import java.util.Optional;

@Dao
public interface UserDao {

    @Transaction
    @Query("SELECT * from Users WHERE Username Like:username AND Password LIKE:password AND Active=1 LIMIT 1")
    Optional<UserAndUsertypes> findByUsernameAndPassword(String username, String password);

    @Insert
    void insertUser(User user);

    @Query("SELECT * from Users WHERE UserId LIKE:id")
    User getUserById(int id);
}
