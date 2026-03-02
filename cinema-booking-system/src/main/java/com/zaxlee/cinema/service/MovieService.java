package com.zaxlee.cinema.service;

import com.zaxlee.cinema.dto.request.CreateMovieRequest;
import com.zaxlee.cinema.dto.response.MovieResponse;
import com.zaxlee.cinema.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final Map<Long, Movie> movieStore = new ConcurrentHashMap<>();
    private final AtomicLong movieIdGenerator = new AtomicLong(1);

    public List<MovieResponse> getAllMovies() {
        return movieStore.values()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse createMovie(CreateMovieRequest request) {

        Long id = movieIdGenerator.getAndIncrement();

        Movie movie = new Movie(
                id,
                request.getTitle(),
                request.getShowTime(),
                request.getTotalSeats()
        );

        movieStore.put(id, movie);

        return mapToResponse(movie);
    }

    public void deleteMovie(Long movieId) {
        if (!movieStore.containsKey(movieId)) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }

        movieStore.remove(movieId);
    }

    public Movie findMovieById(Long movieId) {
        Movie movie = movieStore.get(movieId);

        if (movie == null) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }

        return movie;
    }

    private MovieResponse mapToResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getShowTime(),
                movie.getTotalSeats(),
                movie.getAvailableSeats()
        );
    }
}