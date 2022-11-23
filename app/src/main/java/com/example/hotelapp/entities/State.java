package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "States")
public class State {
    @PrimaryKey
    private int StateId;

    @NotNull
    public String getStateName() {
        return stateName;
    }

    @ColumnInfo(name = "StateName")
    @NotNull
    private String stateName;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getStateId() {
        return StateId;
    }

    public void setStateId(int stateId) {
        StateId = stateId;
    }

    public void setStateName(@NotNull String stateName) {
        this.stateName = stateName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
