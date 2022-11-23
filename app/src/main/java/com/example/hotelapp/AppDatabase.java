package com.example.hotelapp;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

import kotlin.jvm.JvmField;

@Database(entities = {User.class, UserType.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    @JvmField
    static
    Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
