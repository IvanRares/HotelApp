package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.entities.RoomType;

public class ImageAndRoomType {
    @Embedded
    public Image image;
    @Relation(
            parentColumn = "RoomTypeId",
            entityColumn="RoomTypeId"
    )
    public RoomType roomType;
}
