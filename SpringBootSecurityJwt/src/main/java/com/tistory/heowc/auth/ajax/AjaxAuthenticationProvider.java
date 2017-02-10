package com.tistory.heowc.auth.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.tistory.heowc.auth.UserDetailsImpl;
import com.tistory.heowc.auth.UserDetailsServiceImpl;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getCredentials().toString();
		UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
		return new AjaxAuthenticationToken(userDetails.getMember(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AjaxAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
