package com.example.hotelapp;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hotelapp.access_objects.RoomDao;
import com.example.hotelapp.access_objects.RoomTypeDao;
import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.State;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

import kotlin.jvm.JvmField;

@Database(entities = {User.class, UserType.class,RoomType.class,State.class,Room.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RoomTypeDao roomTypeDao();
    public abstract RoomDao roomDao();

    @JvmField
    static
    Migration MIGRATION_1_2 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
