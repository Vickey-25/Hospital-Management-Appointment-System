package com.hospital.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
}