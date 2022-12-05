package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.Offer;
import com.example.hotelapp.entities.Price;
import com.example.hotelapp.entities.RoomType;

public class OfferAndPrices {
    @Embedded
    public Price price;
    @Relation(parentColumn = "PriceId",
            entityColumn = "PriceId")
    public Offer offer;
    @Relation(
            parentColumn = "RoomTypeId",
            entityColumn = "RoomTypeId"
    )
    public RoomType roomType;
}
