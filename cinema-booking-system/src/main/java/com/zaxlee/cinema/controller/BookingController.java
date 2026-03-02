package com.zaxlee.cinema.controller;

import com.zaxlee.cinema.dto.request.CreateBookingRequest;
import com.zaxlee.cinema.dto.response.BookingResponse;
import com.zaxlee.cinema.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        BookingResponse response = bookingService.createBooking(request, username);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long id) {

        BookingResponse response = bookingService.getBooking(id);

        return ResponseEntity.ok(response);
    }
}