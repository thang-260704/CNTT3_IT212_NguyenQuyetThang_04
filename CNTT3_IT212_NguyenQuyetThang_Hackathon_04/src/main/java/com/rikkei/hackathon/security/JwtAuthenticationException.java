package com.rikkei.hackathon.security;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    private final JwtErrorCode errorCode;

    public JwtAuthenticationException(JwtErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public JwtErrorCode getErrorCode() {
        return errorCode;
    }
}
