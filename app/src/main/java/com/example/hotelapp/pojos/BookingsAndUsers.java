package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.hotelapp.entities.Booking;
import com.example.hotelapp.entities.BookingRoom;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.State;
import com.example.hotelapp.entities.User;

import java.util.List;

public class BookingsAndUsers {
    @Embedded
    public Booking booking;

    @Relation(
            parentColumn = "UserId",
            entityColumn = "UserId"
    )
    public User user;

    @Relation(
            parentColumn = "StateId",
            entityColumn = "StateId"
    )
    public State state;
}
