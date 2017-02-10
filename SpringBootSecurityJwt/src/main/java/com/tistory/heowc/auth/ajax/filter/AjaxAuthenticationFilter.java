package com.tistory.heowc.auth.ajax.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.ajax.AjaxAuthenticationToken;
import com.tistory.heowc.domain.Member;

public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	ObjectMapper objectMapper;
	
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
		System.out.println(request.getRequestURI());
		Member member = objectMapper.readValue(request.getReader(), Member.class);
		System.out.println(member.toString());
		return getAuthenticationManager().authenticate(new AjaxAuthenticationToken(member.getId()));
	}
}
