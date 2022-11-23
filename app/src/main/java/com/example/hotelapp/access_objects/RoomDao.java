package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.hotelapp.entities.Room;

@Dao
public interface RoomDao {
    @Query("SELECT * from Rooms WHERE RoomName Like:roomName LIMIT 1")
    Room findByRoomName(String roomName);
}
