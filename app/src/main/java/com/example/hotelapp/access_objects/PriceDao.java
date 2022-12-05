package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelapp.entities.Price;
import com.example.hotelapp.pojos.PriceAndRoomTypes;

import java.util.List;

@Dao
public interface PriceDao {
    @Query("SELECT * from Prices WHERE Active = 1 AND IsOffer = 0")
    LiveData<List<PriceAndRoomTypes>> getPrices();

    @Query("SELECT * from Prices WHERE PriceId LIKE:id")
    PriceAndRoomTypes getPriceById(int id);

    @Update
    void updatePrice(Price price);

    @Insert
    void insertPrice(Price price);
}
