package com.zaxlee.cinema.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.NOT_FOUND,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ApiErrorResponse> handleSeatConflict(
            SeatAlreadyBookedException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.CONFLICT,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        message,
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI()
        );
    }

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(
            Exception ex,
            HttpStatus status,
            String path) {

        return ResponseEntity.status(status)
                .body(new ApiErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        ex.getMessage(),
                        path
                ));
    }
}