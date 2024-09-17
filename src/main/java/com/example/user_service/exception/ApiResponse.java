package com.example.user_service.exception;

import java.time.LocalDateTime;
import lombok.Getter;



@Getter
public class ApiResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
    private final String details;

    public ApiResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

}
