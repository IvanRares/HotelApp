package com.example.hotelapp.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndUsertypes {
    @Embedded public User user;
    @Relation(
            parentColumn = "UserTypeId",
            entityColumn="UserTypeId"
    )
    public UserType userType;
}
