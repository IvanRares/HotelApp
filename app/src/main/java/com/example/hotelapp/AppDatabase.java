package com.example.hotelapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hotelapp.access_objects.RoomDao;
import com.example.hotelapp.access_objects.RoomTypeDao;
import com.example.hotelapp.access_objects.UserDao;
import com.example.hotelapp.entities.Amenity;
import com.example.hotelapp.entities.AmenityBooking;
import com.example.hotelapp.entities.Booking;
import com.example.hotelapp.entities.BookingRoom;
import com.example.hotelapp.entities.Image;
import com.example.hotelapp.entities.Offer;
import com.example.hotelapp.entities.Price;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.State;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

import kotlin.jvm.JvmField;

@Database(entities = {User.class, UserType.class,RoomType.class,State.class,Room.class, Amenity.class, AmenityBooking.class, Booking.class, BookingRoom.class, Image.class, Offer.class, Price.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RoomTypeDao roomTypeDao();
    public abstract RoomDao roomDao();

    private static volatile AppDatabase INSTANCE;
    public static synchronized AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE=create(context);
        }
        return INSTANCE;
    }

    private static AppDatabase create(final Context context) {
        return androidx.room.Room.databaseBuilder(
                context,
                AppDatabase.class,
                "HotelDb.db").allowMainThreadQueries().build();
    }
}


