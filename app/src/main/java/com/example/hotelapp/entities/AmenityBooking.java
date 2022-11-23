package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AmenityBookings")
public class AmenityBooking {
    @PrimaryKey
    private int AmenityBookingId;

    @ColumnInfo(name = "AmenityId")
    private int amenityId;

    @ColumnInfo(name = "BookingId")
    private int bookingId;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getAmenityBookingId() {
        return AmenityBookingId;
    }

    public void setAmenityBookingId(int amenityBookingId) {
        AmenityBookingId = amenityBookingId;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
