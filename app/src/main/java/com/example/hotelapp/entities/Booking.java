package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "Bookings")
public class Booking {
    private static Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @PrimaryKey
    private int BookingId;

    @ColumnInfo(name = "UserId")
    private int userId;

    @ColumnInfo(name = "StardDate")
    @NotNull
    private String stardDate;

    @ColumnInfo(name = "EndDate")
    @NotNull
    private String endDate;

    @ColumnInfo(name = "TotalPrice")
    private float totalPrice;

    @ColumnInfo(name = "StateId")
    private int stateId;

    @ColumnInfo(name = "Active",defaultValue = "1")
    private boolean active;

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStardDate() {
        return stardDate;
    }

    public void setStardDate(Date stardDate) {
        this.stardDate = formatter.format(stardDate);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = formatter.format(endDate);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
