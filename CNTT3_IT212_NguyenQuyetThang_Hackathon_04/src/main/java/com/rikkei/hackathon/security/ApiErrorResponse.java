package com.rikkei.hackathon.security;

import java.time.Instant;

public record ApiErrorResponse(
        String error,
        String message,
        Instant timestamp,
        String path
) {
}
