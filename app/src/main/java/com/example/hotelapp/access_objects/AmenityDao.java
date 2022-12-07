package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelapp.entities.Amenity;

import java.util.List;

@Dao
public interface AmenityDao {

    @Query("SELECT * from Amenities WHERE Active = 1")
    LiveData<List<Amenity>> getAmenities();

    @Query("SELECT * from Amenities WHERE Active = 1")
    List<Amenity> getAllAmenities();

    @Query("SELECT * FROM Amenities WHERE AmenityId LIKE:id")
    Amenity getAmenityById(int id);

    @Update
    void updateAmenity(Amenity item);

    @Insert
    void insertAmenity(Amenity amenity);
}
