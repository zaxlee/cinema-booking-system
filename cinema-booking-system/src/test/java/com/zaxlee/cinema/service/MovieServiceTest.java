package com.zaxlee.cinema.service;

import com.zaxlee.cinema.dto.request.CreateMovieRequest;
import com.zaxlee.cinema.dto.response.MovieResponse;
import com.zaxlee.cinema.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
    }

    @Test
    void shouldCreateMovieSuccessfully() {
        CreateMovieRequest request = new CreateMovieRequest();
        request.setTitle("Test Movie");
        request.setShowTime(LocalDateTime.now());
        request.setTotalSeats(100);

        MovieResponse response = movieService.createMovie(request);

        assertNotNull(response);
        assertEquals("Test Movie", response.getTitle());
        assertEquals(100, response.getTotalSeats());
        assertEquals(100, response.getAvailableSeats());
    }

    @Test
    void shouldReturnAllMovies() {
        CreateMovieRequest request = new CreateMovieRequest();
        request.setTitle("Movie A");
        request.setShowTime(LocalDateTime.now());
        request.setTotalSeats(50);

        movieService.createMovie(request);

        List<MovieResponse> movies = movieService.getAllMovies();

        assertFalse(movies.isEmpty());
        assertEquals(1, movies.size());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingMovie() {
        assertThrows(ResourceNotFoundException.class,
                () -> movieService.deleteMovie(999L));
    }
}