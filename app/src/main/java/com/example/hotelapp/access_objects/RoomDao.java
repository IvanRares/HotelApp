package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.hotelapp.entities.Room;
import com.example.hotelapp.pojos.RoomAndRoomTypes;
import com.example.hotelapp.pojos.UserAndUsertypes;

import java.util.List;
import java.util.Optional;

@Dao
public interface RoomDao {
    @Query("SELECT * from Rooms LIMIT 1")
    Room findByRoomName();

    @Transaction
    @Query("SELECT * from Rooms WHERE Active = 1")
    List<RoomAndRoomTypes> getRooms();
}
