package com.rikkei.hackathon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        JwtErrorCode errorCode = resolveErrorCode(authException);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                errorCode.name(),
                authException.getMessage(),
                Instant.now(),
                request.getRequestURI()
        );

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

    private JwtErrorCode resolveErrorCode(AuthenticationException exception) {
        if (exception instanceof JwtAuthenticationException jwtException) {
            return jwtException.getErrorCode();
        }
        return JwtErrorCode.UNAUTHORIZED;
    }
}
