package com.tistory.heowc.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		System.out.println("getPreAuthenticatedPrincipal()");
		return request;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		System.out.println("getPreAuthenticatedCredentials()");
		return request;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		System.out.println("successfulAuthentication()");
		super.successfulAuthentication(request, response, authResult);
	}

	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		System.out.println("unsuccessfulAuthentication()");
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
