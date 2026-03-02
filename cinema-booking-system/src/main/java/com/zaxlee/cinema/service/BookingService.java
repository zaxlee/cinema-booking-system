package com.zaxlee.cinema.service;

import com.zaxlee.cinema.dto.request.CreateBookingRequest;
import com.zaxlee.cinema.dto.response.BookingResponse;
import com.zaxlee.cinema.model.Booking;
import com.zaxlee.cinema.model.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookingService {

    private final MovieService movieService;

    private final Map<Long, Booking> bookingStore = new ConcurrentHashMap<>();
    private final AtomicLong bookingIdGenerator = new AtomicLong(1);

    public BookingService(MovieService movieService) {
        this.movieService = movieService;
    }

    public BookingResponse createBooking(CreateBookingRequest request, String username) {

        Movie movie = movieService.findMovieById(request.getMovieId());

        int seatNumber = request.getSeatNumber();

        if (!movie.isSeatAvailable(seatNumber)) {
            throw new IllegalStateException("Seat is not available");
        }

        movie.bookSeat(seatNumber);

        Long bookingId = bookingIdGenerator.getAndIncrement();

        Booking booking = new Booking(
                bookingId,
                movie.getId(),
                seatNumber,
                username,
                LocalDateTime.now()
        );

        bookingStore.put(bookingId, booking);

        return mapToResponse(booking);
    }

    public BookingResponse getBooking(Long bookingId) {

        Booking booking = bookingStore.get(bookingId);

        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }

        return mapToResponse(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getMovieId(),
                booking.getSeatNumber(),
                booking.getUsername(),
                booking.getBookingTime()
        );
    }
}