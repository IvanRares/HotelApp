package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.hotelapp.entities.RoomType;


@Dao
public interface RoomTypeDao {
    @Query("SELECT * from RoomTypes WHERE RoomTypeName Like:roomTypeName LIMIT 1")
    RoomType findByRoomTypeName(String roomTypeName);
}
