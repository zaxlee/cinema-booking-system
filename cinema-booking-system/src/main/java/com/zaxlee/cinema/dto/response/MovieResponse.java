package com.zaxlee.cinema.dto.response;

import java.time.LocalDateTime;

public class MovieResponse {

    private final Long id;
    private final String title;
    private final LocalDateTime showTime;
    private final int totalSeats;
    private final int availableSeats;

    public MovieResponse(Long id,
                         String title,
                         LocalDateTime showTime,
                         int totalSeats,
                         int availableSeats) {
        this.id = id;
        this.title = title;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
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

    public int getAvailableSeats() {
        return availableSeats;
    }
}