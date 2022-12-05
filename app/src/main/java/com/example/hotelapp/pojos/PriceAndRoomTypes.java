package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.Price;
import com.example.hotelapp.entities.RoomType;

public class PriceAndRoomTypes {
    @Embedded
    public Price price;
    @Relation(
            parentColumn = "RoomTypeId",
            entityColumn="RoomTypeId"
    )
    public RoomType roomType;
}
