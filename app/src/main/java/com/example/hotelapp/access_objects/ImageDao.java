package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.pojos.ImageAndRoomType;
import com.example.hotelapp.pojos.RoomAndRoomTypes;

import java.util.List;
@Dao
public interface ImageDao {
    @Query("SELECT ImageData from Images WHERE RoomTypeId=:id")
    List<byte[]> getImagesDataForRoomType(int id);

    @Transaction
    @Query("SELECT * from Images WHERE Active = 1")
    LiveData<List<ImageAndRoomType>> getImages();

    @Query("SELECT * FROM Images WHERE ImageId LIKE:id")
    ImageAndRoomType getImageById(int id);

    @Update
    void updateImage(Image image);

    @Insert
    void insertImage(Image image);
}
