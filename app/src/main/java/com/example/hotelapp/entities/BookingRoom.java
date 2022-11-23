package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookingRooms")
public class BookingRoom {
    @PrimaryKey
    private int BookingRoomId;

    @ColumnInfo(name = "BookingId")
    private int bookingId;

    @ColumnInfo(name = "RoomId")
    private int roomId;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getBookingRoomId() {
        return BookingRoomId;
    }

    public void setBookingRoomId(int bookingRoomId) {
        BookingRoomId = bookingRoomId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
