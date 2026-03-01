package com.zaxlee.cinema.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Movie {

    private final Long id;
    private final String title;
    private final LocalDateTime showTime;
    private final int totalSeats;
    private final Set<Integer> bookedSeats = new HashSet<>();

    public Movie(Long id, String title, LocalDateTime showTime, int totalSeats) {
        this.id = id;
        this.title = title;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public Set<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber > 0 &&
                seatNumber <= totalSeats &&
                !bookedSeats.contains(seatNumber);
    }

    public void bookSeat(int seatNumber) {
        bookedSeats.add(seatNumber);
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats.size();
    }
}