package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Rooms",
        foreignKeys = {
                @ForeignKey(entity = RoomType.class,
                        parentColumns = "RoomTypeId",
                        childColumns = "RoomTypeId",
                        onDelete = ForeignKey.CASCADE)
        },indices = {
        @Index(name = "Rooms_roomNameUnique",value = "RoomName",unique = true,orders = Index.Order.ASC),
        @Index(name = "Rooms_IX_Rooms_RoomTypeId", value = "RoomTypeId", unique = false, orders = Index.Order.ASC)})
public class Room {
    @PrimaryKey
    private int RoomId;

    @ColumnInfo(name = "RoomTypeId")
    private int roomTypeId;

    @ColumnInfo(name="RoomName")
    @NotNull
    private String roomName;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @NotNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NotNull String roomName) {
        this.roomName = roomName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
