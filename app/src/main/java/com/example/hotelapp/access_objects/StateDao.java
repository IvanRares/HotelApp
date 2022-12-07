package com.example.hotelapp.access_objects;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.hotelapp.entities.State;

import java.util.List;

@Dao
public interface StateDao {

    @Query("SELECT * from States WHERE Active=1")
    List<State> getStates();
}
