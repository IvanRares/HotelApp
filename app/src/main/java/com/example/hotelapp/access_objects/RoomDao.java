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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRoom(Room item);

    @Query("select *\n" +
            "    from Rooms\n" +
            "    where RoomId not in\n" +
            "    (select RoomId\n" +
            "    from Bookings b\n" +
            "    join BookingRooms br on b.BookingId=br.BookingId\n" +
            "    where (((date(StartDate) <= date(:arrivalDate) and date(EndDate)>= date(:arrivalDate))\n" +
            "    or (date(StartDate) < date(:departureDate) and date(EndDate)>=date(:departureDate))\n" +
            "    or (date(:arrivalDate)<=date(StartDate) and date(:departureDate)>= date(StartDate))) and b.StateId!=2)\n" +
            "    ) and Active=1 and RoomTypeId Like :roomTypeId\n")
    List<Room> getEmptyRoomsForRoomType(String arrivalDate,String departureDate,int roomTypeId);
}
