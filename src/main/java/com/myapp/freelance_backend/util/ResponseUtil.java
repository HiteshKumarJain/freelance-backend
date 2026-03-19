package com.myapp.freelance_backend.util;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<ApiResponse> success(int status, String message, Object data) {
        return ResponseEntity.status(status)
                .body(new ApiResponse("success", message, data));
    }

    public static ResponseEntity<ApiResponse> error(int status, String message) {
        return ResponseEntity.status(status)
                .body(new ApiResponse("error", message, null));
    }
}
