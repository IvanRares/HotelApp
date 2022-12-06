package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Users",
        foreignKeys = {
                @ForeignKey(entity = UserType.class,
                        parentColumns = "UserTypeId",
                        childColumns = "UserTypeId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(name = "Users_uniqueUsernameActive",value = "Username", unique = true, orders = Index.Order.ASC),
                @Index(name = "Users_IX_Users_UserTypeId", value = "UserTypeId", unique = false, orders = Index.Order.ASC)
        })
public class User {

    @PrimaryKey(autoGenerate = true)
    private int UserId;

    @ColumnInfo(name = "UserTypeId")
    private int typeId;

    @ColumnInfo(name = "Username")
    @NotNull
    private String username;

    @ColumnInfo(name = "Password")
    @NotNull
    private String password;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active=true;

    public User(int typeId, @NotNull String username, @NotNull String password) {
        this.typeId = typeId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
