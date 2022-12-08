package com.example.hotelapp.access_objects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotelapp.entities.AmenityBooking;
import com.example.hotelapp.entities.Booking;
import com.example.hotelapp.entities.BookingRoom;
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

    @Transaction
    @Query("SELECT * from Bookings WHERE BookingId LIKE:id")
    BookingsAndUsers getBookingById(int id);

    @Update
    void updateBooking(Booking booking);

    @Insert
    long insertBooking(Booking booking);

    @Insert
    void insertAmenityBooking(AmenityBooking amenityBooking);

    @Insert
    void insertBookingRoom(BookingRoom bookingRoom);
}
