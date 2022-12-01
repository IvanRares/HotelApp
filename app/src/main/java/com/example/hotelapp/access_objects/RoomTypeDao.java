package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.hotelapp.entities.RoomType;

import java.util.List;


@Dao
public interface RoomTypeDao {
    @Query("SELECT * from RoomTypes WHERE RoomTypeName Like:roomTypeName LIMIT 1")
    RoomType findByRoomTypeName(String roomTypeName);

    @Query("SELECT * from RoomTypes WHERE Active = 1")
    List<RoomType> getAllRoomTypes();
}
