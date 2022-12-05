package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelapp.entities.Offer;
import com.example.hotelapp.pojos.OfferAndPrices;

import java.util.List;

@Dao
public interface OfferDao {
    @Query("SELECT * from Prices WHERE Active=1 AND IsOffer=1")
    LiveData<List<OfferAndPrices>> getOffers();

    @Query("SELECT * from Prices WHERE PriceId LIKE:id")
    OfferAndPrices getOfferById(int id);

    @Update
    void updateOffer(Offer offer);

    @Insert
    void insertOffer(Offer offer);
}
