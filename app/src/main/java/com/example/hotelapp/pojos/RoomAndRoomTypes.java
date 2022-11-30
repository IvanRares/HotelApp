package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;

public class RoomAndRoomTypes {
    @Embedded
    public Room room;
    @Relation(
            parentColumn = "RoomTypeId",
            entityColumn="RoomTypeId"
    )
    public RoomType roomType;
}
