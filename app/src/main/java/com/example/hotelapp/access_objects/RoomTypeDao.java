package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.RoomAndRoomTypes;
import com.example.hotelapp.pojos.RoomTypeAndImage;

import java.util.List;

import java.util.List;


@Dao
public interface RoomTypeDao {
    @Query("SELECT * from RoomTypes WHERE RoomTypeName Like:roomTypeName LIMIT 1")
    RoomType findByRoomTypeName(String roomTypeName);

    @Query("SELECT * from RoomTypes WHERE Active = 1")
    List<RoomType> getAllRoomTypes();
    @Transaction
    @Query("SELECT * from RoomTypes WHERE Active = 1")
    List<RoomTypeAndImage> getRoomTypes();

    @Query("SELECT * from RoomTypes WHERE RoomTypeId LIKE:id LIMIT 1")
    RoomType getRoomType(int id);

    @Query("SELECT * from RoomTypes WHERE Active = 1")
    LiveData<List<RoomType>> getLiveRoomTypes();


    @Query("select count(*)\n" +
            "    from Rooms\n" +
            "    where RoomId not in\n" +
            "    (select RoomId\n" +
            "    from Bookings b\n" +
            "    join BookingRooms br on b.BookingId=br.BookingId\n" +
            "    where (((date(StartDate) <= date(:arrivalDate) and date(EndDate)>= date(:arrivalDate))\n" +
            "    or (date(StartDate) < date(:departureDate) and date(EndDate)>=date(:departureDate))\n" +
            "    or (date(:arrivalDate)<=date(StartDate) and date(:departureDate)>= date(StartDate))) and b.StateId!=2)\n" +
            "    ) and Active=1 and RoomTypeId Like :roomTypeId\n")
    int getEmptyRoomsForRoomType(String arrivalDate,String departureDate,int roomTypeId);
    @Update
    void updateRoomType(RoomType roomType);
}
