package com.rikkei.hackathon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtAuthenticationEntryPointTest {

    @Test
    void shouldReturnJson401WhenTokenIsMissing() throws Exception {
        JwtAuthenticationEntryPoint entryPoint = new JwtAuthenticationEntryPoint(new ObjectMapper());
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/courses");
        MockHttpServletResponse response = new MockHttpServletResponse();

        entryPoint.commence(
                request,
                response,
                new JwtAuthenticationException(JwtErrorCode.MISSING_TOKEN, "Authorization token is missing")
        );

        assertEquals(401, response.getStatus());
        assertTrue(response.getContentAsString().contains("MISSING_TOKEN"));
        assertTrue(response.getContentAsString().contains("Authorization token is missing"));
    }
}
