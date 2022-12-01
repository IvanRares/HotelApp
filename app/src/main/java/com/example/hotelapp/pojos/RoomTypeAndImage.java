package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

public class RoomTypeAndImage {
    @Embedded
    public RoomType roomType;
    @Relation(
            parentColumn = "RoomTypeId",
            entityColumn="RoomTypeId"
    )
    public Image image;
}
