package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.RoomAndRoomTypes;
import com.example.hotelapp.pojos.RoomTypeAndImage;

import java.util.List;

import java.util.List;


@Dao
public interface RoomTypeDao {
    @Query("SELECT * from RoomTypes WHERE RoomTypeName Like:roomTypeName LIMIT 1")
    RoomType findByRoomTypeName(String roomTypeName);

    @Query("SELECT * from RoomTypes WHERE Active = 1")
    List<RoomType> getAllRoomTypes();
    @Transaction
    @Query("SELECT * from RoomTypes WHERE Active = 1")
    List<RoomTypeAndImage> getRoomTypes();

    @Query("SELECT * from RoomTypes WHERE RoomTypeId LIKE:id LIMIT 1")
    RoomType getRoomType(int id);

    @Query("SELECT * from RoomTypes WHERE Active = 1")
    LiveData<List<RoomType>> getLiveRoomTypes();

    @Update
    void updateRoomType(RoomType roomType);
}
