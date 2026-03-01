package com.zaxlee.cinema.dto.response;

import java.time.LocalDateTime;

public class BookingResponse {

    private final Long id;
    private final Long movieId;
    private final Integer seatNumber;
    private final String username;
    private final LocalDateTime bookingTime;

    public BookingResponse(Long id,
                           Long movieId,
                           Integer seatNumber,
                           String username,
                           LocalDateTime bookingTime) {
        this.id = id;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.username = username;
        this.bookingTime = bookingTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}