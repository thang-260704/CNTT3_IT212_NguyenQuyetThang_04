package com.rikkei.hackathon.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public JwtAuthenticationFilter(JwtService jwtService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtService = jwtService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authHeader)) {
            authenticationEntryPoint.commence(
                    request,
                    response,
                    new JwtAuthenticationException(JwtErrorCode.MISSING_TOKEN, "Authorization token is missing")
            );
            return;
        }

        if (!authHeader.startsWith(BEARER_PREFIX)) {
            authenticationEntryPoint.commence(
                    request,
                    response,
                    new JwtAuthenticationException(JwtErrorCode.INVALID_TOKEN, "Authorization header must start with Bearer")
            );
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        if (!StringUtils.hasText(token)) {
            authenticationEntryPoint.commence(
                    request,
                    response,
                    new JwtAuthenticationException(JwtErrorCode.MISSING_TOKEN, "Bearer token value is empty")
            );
            return;
        }

        try {
            String username = jwtService.extractUsername(token);
            if (StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException exception) {
            authenticationEntryPoint.commence(
                    request,
                    response,
                    new JwtAuthenticationException(JwtErrorCode.EXPIRED_TOKEN, "JWT token has expired")
            );
        } catch (JwtException | IllegalArgumentException exception) {
            authenticationEntryPoint.commence(
                    request,
                    response,
                    new JwtAuthenticationException(JwtErrorCode.INVALID_TOKEN, "JWT token is invalid")
            );
        }
    }
}
