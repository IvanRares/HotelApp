package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Amenities")
public class Amenity {
    @PrimaryKey(autoGenerate = true)
    private int AmenityId;

    @ColumnInfo(name = "AmenityName")
    @NotNull
    private String amenityName;

    @ColumnInfo(name = "Price")
    private float price;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

    public Amenity(String amenityName, float price) {
        this.amenityName = amenityName;
        this.price = price;
    }

    public int getAmenityId() {
        return AmenityId;
    }

    public void setAmenityId(int amenityId) {
        AmenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
