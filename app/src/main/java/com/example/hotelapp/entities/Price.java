package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "Prices",
        foreignKeys = {
                @ForeignKey(entity = RoomType.class,
                        parentColumns = "RoomTypeId",
                        childColumns = "RoomTypeId",
                        onDelete = ForeignKey.CASCADE)
        }, indices = {@Index(name = "Prices_IX_Prices_RoomTypeId", value = "RoomTypeId", unique = false, orders = Index.Order.ASC)})
public class Price {
    private static Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @PrimaryKey(autoGenerate = true)
    private int PriceId;

    @ColumnInfo(name = "RoomTypeId", defaultValue = "0")
    private int roomTypeId;

    @ColumnInfo(name = "StartDate")
    @NotNull
    private String startDate;

    @ColumnInfo(name = "EndDate")
    @NotNull
    private String endDate;

    @ColumnInfo(name = "PriceValue")
    private float priceValue;

    @ColumnInfo(name = "IsOffer")
    private boolean isOffer;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

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
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull Date startDate) {
        this.startDate = formatter.format(startDate);
    }

    public void setStartDate(@NotNull String startDate) {
        this.startDate = startDate;
    }

    @NotNull
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull Date endDate) {
        this.endDate = formatter.format(endDate);
    }

    public void setEndDate(@NotNull String endDate) {
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
