package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "RoomTypes")
public class RoomType {
    @PrimaryKey(autoGenerate = true)
    private int RoomTypeId;

    @ColumnInfo(name = "RoomTypeName")
    @NotNull
    private String roomTypeName;

    @ColumnInfo(name = "RoomTypeDescription")
    @NotNull
    private String roomTypeDescription;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

    public int getRoomTypeId() {
        return RoomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        RoomTypeId = roomTypeId;
    }

    @NotNull
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(@NotNull String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @NotNull
    public String getRoomTypeDescription() {
        return roomTypeDescription;
    }

    public void setRoomTypeDescription(@NotNull String roomTypeDescription) {
        this.roomTypeDescription = roomTypeDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
