package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(tableName = "Prices",
        foreignKeys = {
                @ForeignKey(entity = RoomType.class,
                        parentColumns = "RoomTypeId",
                        childColumns = "RoomTypeId",
                        onDelete = ForeignKey.CASCADE)
        },indices = {@Index(name = "Prices_IX_Prices_RoomTypeId", value = "RoomTypeId", unique = false, orders = Index.Order.ASC)})
public class Price {
    @PrimaryKey
    private int PriceId;

    @ColumnInfo(name = "RoomTypeId")
    private int roomTypeId;

    @ColumnInfo(name = "StardDate")
    @NotNull
    private Date startDate;

    @ColumnInfo(name = "EndDate")
    @NotNull
    private Date endDate;

    @ColumnInfo(name = "PriceValue")
    private float priceValue;

    @ColumnInfo(name = "IsOffer", defaultValue = "1")
    private boolean isOffer;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull Date startDate) {
        this.startDate = startDate;
    }

    @NotNull
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull Date endDate) {
        this.endDate = endDate;
    }

    public float getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(float priceValue) {
        this.priceValue = priceValue;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}