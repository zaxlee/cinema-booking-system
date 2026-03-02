package com.zaxlee.cinema.service;

import com.zaxlee.cinema.dto.request.CreateBookingRequest;
import com.zaxlee.cinema.dto.request.CreateMovieRequest;
import com.zaxlee.cinema.exception.SeatAlreadyBookedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private MovieService movieService;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
        bookingService = new BookingService(movieService);

        CreateMovieRequest request = new CreateMovieRequest();
        request.setTitle("Test Movie");
        request.setShowTime(LocalDateTime.now());
        request.setTotalSeats(10);

        movieService.createMovie(request);
    }

    @Test
    void shouldCreateBookingSuccessfully() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setMovieId(1L);
        request.setSeatNumber(5);

        var response = bookingService.createBooking(request, "user");

        assertNotNull(response);
        assertEquals(5, response.getSeatNumber());
        assertEquals("user", response.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenSeatAlreadyBooked() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setMovieId(1L);
        request.setSeatNumber(3);

        bookingService.createBooking(request, "user");

        assertThrows(SeatAlreadyBookedException.class,
                () -> bookingService.createBooking(request, "user"));
    }
}