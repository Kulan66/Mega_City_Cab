package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.BookingDAO;
import com.example.mega_city_cab.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private BookingService bookingService;
    private BookingDAO bookingDAO;
    private Booking booking;

    @BeforeEach
    void setUp() {
        bookingDAO = new BookingDAO() {
            private List<Booking> bookings = new ArrayList<>();

            @Override
            public void addBooking(Booking booking) throws SQLException {
                bookings.add(booking);
            }

            @Override
            public Booking getBooking(int bookingID) throws SQLException {
                return bookings.stream()
                        .filter(b -> b.getBookingID() == bookingID)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Booking> getAllBookings() throws SQLException {
                return new ArrayList<>(bookings);
            }

            @Override
            public void updateBooking(Booking booking) throws SQLException {
                int index = bookings.indexOf(getBooking(booking.getBookingID()));
                if (index >= 0) {
                    bookings.set(index, booking);
                }
            }

            @Override
            public void deleteBooking(int bookingID) throws SQLException {
                bookings.removeIf(b -> b.getBookingID() == bookingID);
            }
        };
        bookingService = new BookingService();
        bookingService.setBookingDAO(bookingDAO); // Assuming there's a setter for BookingDAO in BookingService

        booking = new Booking();
        booking.setBookingID(1);
        booking.setCustomerID(1);
        booking.setDriverID(1);
        booking.setCarID(1);
        booking.setDestination("Destination");
        booking.setPaymentMethod("Cash");
        booking.setDistanceKm(10.0);
        booking.setBasePrice(1000.0);
        booking.setTax(50.0);
        booking.setDiscount(10.0);
        booking.setTotalPrice(945.0);
        booking.setBookingDate(new Date());
    }

    @Test
    void addBooking() throws SQLException {
        bookingService.addBooking(booking);
        Booking retrievedBooking = bookingService.getBooking(1);
        assertNotNull(retrievedBooking);
        assertEquals(booking.getBookingID(), retrievedBooking.getBookingID());
    }

    @Test
    void getBooking() throws SQLException {
        bookingService.addBooking(booking);
        Booking retrievedBooking = bookingService.getBooking(1);
        assertNotNull(retrievedBooking);
        assertEquals(booking.getBookingID(), retrievedBooking.getBookingID());
    }

    @Test
    void getAllBookings() throws SQLException {
        bookingService.addBooking(booking);
        List<Booking> retrievedBookings = bookingService.getAllBookings();
        assertNotNull(retrievedBookings);
        assertEquals(1, retrievedBookings.size());
    }

    @Test
    void updateBooking() throws SQLException {
        bookingService.addBooking(booking);
        booking.setDestination("New Destination");
        bookingService.updateBooking(booking);
        Booking retrievedBooking = bookingService.getBooking(1);
        assertNotNull(retrievedBooking);
        assertEquals("New Destination", retrievedBooking.getDestination());
    }

    @Test
    void deleteBooking() throws SQLException {
        bookingService.addBooking(booking);
        bookingService.deleteBooking(1);
        Booking retrievedBooking = bookingService.getBooking(1);
        assertNull(retrievedBooking);
    }
}