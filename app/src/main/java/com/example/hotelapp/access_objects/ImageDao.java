package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.pojos.RoomAndRoomTypes;

import java.util.List;
@Dao
public interface ImageDao {
    @Query("SELECT ImageData from Images WHERE RoomTypeId=:id")
    List<byte[]> getImagesDataForRoomType(int id);
}
