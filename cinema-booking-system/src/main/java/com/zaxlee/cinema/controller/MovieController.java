package com.zaxlee.cinema.controller;

import com.zaxlee.cinema.dto.request.CreateMovieRequest;
import com.zaxlee.cinema.dto.response.MovieResponse;
import com.zaxlee.cinema.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(
            @Valid @RequestBody CreateMovieRequest request) {

        MovieResponse response = movieService.createMovie(request);

        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }
}