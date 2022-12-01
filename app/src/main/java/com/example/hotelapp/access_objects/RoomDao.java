package com.example.hotelapp.access_objects;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.Room;
import com.example.hotelapp.pojos.RoomAndRoomTypes;
import com.example.hotelapp.pojos.UserAndUsertypes;

import java.util.List;
import java.util.Optional;

@Dao
public interface RoomDao {
    @Query("SELECT * from Rooms LIMIT 1")
    Room findByRoomName();

    @Transaction
    @Query("SELECT * from Rooms WHERE Active = 1")
    LiveData<List<RoomAndRoomTypes>> getRooms();

    @Transaction
    @Query("SELECT * from Rooms WHERE RoomId LIKE:id")
    RoomAndRoomTypes getRoomById(int id);

    @Update
    void updateRoom(Room item);

    @Insert(onConflict = OnConflictStrategy.IGNORE,entity = Room.class)
    long insertRoom(Room item);

    @Query("SELECT * from Rooms WHERE RoomId LIKE:id")
    Room getRoomByRoomId(long id);

    @Query("INSERT INTO Rooms(RoomName,RoomTypeId) values('roomnouhere',1)")
    void insertRoom();
}
