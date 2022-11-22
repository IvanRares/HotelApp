package com.example.hotelapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

@Database(entities = {User.class, UserType.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
