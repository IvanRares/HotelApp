package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Images",
        foreignKeys = {
                @ForeignKey(entity = RoomType.class,
                        parentColumns = "RoomTypeId",
                        childColumns = "RoomTypeId",
                        onDelete = ForeignKey.CASCADE)
        },indices = {@Index(name = "Images_IX_Images_RoomTypeId", value = "RoomTypeId", unique = false, orders = Index.Order.ASC)})
public class Image {

    @PrimaryKey
    private int ImageId;

    @ColumnInfo(name = "RoomTypeId")
    private int roomTypeId;

    @ColumnInfo(name = "ImageName")
    @NotNull
    private String imageName;

    @ColumnInfo(name = "ImageDate")
    @NotNull
    private byte[] imageData;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @NotNull
    public String getImageName() {
        return imageName;
    }

    public void setImageName(@NotNull String imageName) {
        this.imageName = imageName;
    }

    @NotNull
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(@NotNull byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
