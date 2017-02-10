package com.tistory.heowc.auth.jwt.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.tistory.heowc.auth.jwt.JwtAuthenticationToken;
import com.tistory.heowc.auth.jwt.JwtFactory;
import com.tistory.heowc.auth.jwt.JwtInfo;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	JwtFactory jwtFactory;
	
	@Autowired
	public JwtAuthenticationFilter(
			RequestMatcher requestMatcher, JwtFactory jwtFactory) {
		super(requestMatcher);
		this.jwtFactory = jwtFactory;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException,
																					IOException,
																					ServletException {
		System.out.println(request.getRequestURI());
		String token = request.getHeader(JwtInfo.HEADER_NAME);
		System.out.println(token);
		
		Boolean isVerify = jwtFactory.verifyToken(token);
		if(isVerify) {
			return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
		} else {
			throw new AccessDeniedException("접근 불가 사용자입니다.");
		}
	}
}
