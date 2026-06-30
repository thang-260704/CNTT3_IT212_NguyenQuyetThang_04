package com.rikkei.hackathon.security;

public enum JwtErrorCode {
    MISSING_TOKEN,
    INVALID_TOKEN,
    EXPIRED_TOKEN,
    ACCESS_DENIED,
    UNAUTHORIZED
}
