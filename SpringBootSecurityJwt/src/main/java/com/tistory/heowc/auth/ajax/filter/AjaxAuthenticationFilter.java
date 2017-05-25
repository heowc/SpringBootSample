package com.tistory.heowc.auth.ajax.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.ajax.AjaxAuthenticationToken;
import com.tistory.heowc.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper;
	
	@Autowired
	public AjaxAuthenticationFilter(
			RequestMatcher requestMatcher, ObjectMapper objectMapper) {
		super(requestMatcher);
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException,
																					IOException,
																					ServletException {
		if(request.getContentType().matches(MediaType.APPLICATION_JSON_VALUE)) {
			Member member = objectMapper.readValue(request.getReader(), Member.class);
			return getAuthenticationManager().authenticate(new AjaxAuthenticationToken(member.getId()));
		} else {
			throw new AccessDeniedException("Don't use content type for " + request.getContentType());
		}
	}
}
