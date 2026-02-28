package com.hospital.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(DoctorNotAvailableException.class)
public ResponseEntity<ApiError> handleDoctorNotAvailable(
        DoctorNotAvailableException ex,
        HttpServletRequest request
) {

    ApiError error = ApiError.builder()
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .status(HttpStatus.CONFLICT.value())
            .timestamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request
    ) {

        ApiError error = ApiError.builder()
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiError> handleResourceNotFound(
        ResourceNotFoundException ex,
        HttpServletRequest request
) {

    ApiError error = ApiError.builder()
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .status(HttpStatus.NOT_FOUND.value())
            .timestamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {

        ApiError error = ApiError.builder()
                .message("Internal Server Error")
                .path(request.getRequestURI())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}