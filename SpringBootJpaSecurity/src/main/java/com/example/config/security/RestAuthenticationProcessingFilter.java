package com.example.config.security;

import com.example.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationProcessingFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (isJson(request)) {
            throw new AuthenticationServiceException("Authentication content-type not supported: " + request.getContentType());
        }

        User user = getUser(request);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
        authRequest.setDetails(user);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private User getUser(HttpServletRequest request) {
        User user = toUser(request);

        if (validateUser(user)) {
            throw new AccessDeniedException("Access Denied");
        }

        return user;
    }

    private boolean validateUser(User user) {
        if (user == null) {
            return false;
        }

        if (StringUtils.isEmpty(user.getId())) {
            return false;
        }

        return StringUtils.isEmpty(user.getPassword());
    }

    private boolean isJson(HttpServletRequest request) {
        return MediaType.APPLICATION_JSON_VALUE.matches(request.getContentType());
    }

    private User toUser(HttpServletRequest request) {
        try {
            return objectMapper.readValue(request.getReader(), User.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
