package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "UserTypes")
public class UserType {


    @PrimaryKey(autoGenerate = true)
    private int UserTypeId;

    @ColumnInfo(name = "UserTypeName")
    @NotNull
    private String name;

    @ColumnInfo(name="Active",defaultValue = "1")
    private boolean active=true;

    public int getUserTypeId() {
        return UserTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        UserTypeId = userTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
