package com.tistory.heowc.auth.ajax;

import com.tistory.heowc.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AjaxAuthenticationProvider implements AuthenticationProvider {

	private final AjaxUserDetailsService userDetailsService;
	
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
