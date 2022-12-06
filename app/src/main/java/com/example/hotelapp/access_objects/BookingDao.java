package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.BookingsAndUsers;

import java.util.List;

@Dao
public interface BookingDao {

    @Transaction
    @Query("SELECT * from Bookings WHERE Active=1")
    LiveData<List<BookingsAndUsers>> getBookings();

    @Query("SELECT * from Rooms JOIN BookingRooms ON BookingRooms.RoomId=Rooms.RoomId WHERE BookingId LIKE:id")
    List<Room> getRoomsByBooking(int id);
}
