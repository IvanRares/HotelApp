package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "AmenityBookings",
        foreignKeys = {
                @ForeignKey(entity = Amenity.class,
                        parentColumns = "AmenityId",
                        childColumns = "AmenityId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Booking.class,
                parentColumns = "BookingId",
                childColumns = "BookingId",
                onDelete = ForeignKey.CASCADE)
        },
indices = {
        @Index(name = "AmenityBookings_IX_AmenityBookings_BookingId",value = "BookingId",unique = false,orders = Index.Order.ASC),
        @Index(name = "AmenityBookings_IX_AmenityBookings_AmenityId",value = "AmenityId",unique = false,orders = Index.Order.ASC)
})
public class AmenityBooking {
    @PrimaryKey(autoGenerate = true)
    private int AmenityBookingId;

    @ColumnInfo(name = "AmenityId")
    private int amenityId;

    @ColumnInfo(name = "BookingId")
    private int bookingId;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

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
