package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.Price;
import com.example.hotelapp.pojos.PriceAndRoomTypes;

import java.util.List;

@Dao
public interface PriceDao {
    @Transaction
    @Query("SELECT * from Prices WHERE Active = 1 AND IsOffer = 0")
    LiveData<List<PriceAndRoomTypes>> getPrices();

    @Transaction
    @Query("SELECT * from Prices WHERE PriceId LIKE:id")
    PriceAndRoomTypes getPriceById(int id);

    @Transaction
    @Query(" select * from Prices\n" +
            "    where (not(date(EndDate)<date(:arrivalDate) or date(StartDate)>date(:departureDate)))\n" +
            "    and Active = 1 and IsOffer=0")
    LiveData<List<PriceAndRoomTypes>> getPricesByDate(String arrivalDate,String departureDate);

    @Transaction
    @Query(" select * from Prices\n" +
            "    where (not(date(EndDate)<date(:arrivalDate) or date(StartDate)>date(:departureDate)))\n" +
            "    and Active = 1 and IsOffer=0")
    List<PriceAndRoomTypes> getAllPricesByDate(String arrivalDate,String departureDate);

    @Transaction
    @Query("SELECT * from Prices WHERE PriceId LIKE:id")
    List<PriceAndRoomTypes> getPriceAsListById(int id);

    @Update
    void updatePrice(Price price);

    @Insert
    long insertPrice(Price price);
}
