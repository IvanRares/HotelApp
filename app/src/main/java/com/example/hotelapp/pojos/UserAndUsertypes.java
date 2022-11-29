package com.example.hotelapp.pojos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hotelapp.entities.User;
import com.example.hotelapp.entities.UserType;

public class UserAndUsertypes {
    @Embedded public User user;
    @Relation(
            parentColumn = "UserTypeId",
            entityColumn="UserTypeId"
    )
    public UserType userType;
}
