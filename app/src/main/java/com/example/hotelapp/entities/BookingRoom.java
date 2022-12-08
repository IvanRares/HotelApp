package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookingRooms",
        foreignKeys = {
                @ForeignKey(entity = Booking.class,
                        parentColumns = "BookingId",
                        childColumns = "BookingId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Room.class,
                        parentColumns = "RoomId",
                        childColumns = "RoomId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(name = "BookingRooms_IX_BookingRooms_BookingId",value = "BookingId",unique = false,orders = Index.Order.ASC),
                @Index(name = "BookingRooms_IX_BookingRooms_RoomId",value = "RoomId",unique = false,orders = Index.Order.ASC)
        })
public class BookingRoom {
    @PrimaryKey(autoGenerate = true)
    private int BookingRoomId;

    @ColumnInfo(name = "BookingId")
    private int bookingId;

    @ColumnInfo(name = "RoomId")
    private int roomId;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

    public BookingRoom(int bookingId, int roomId) {
        this.bookingId = bookingId;
        this.roomId = roomId;
    }

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
