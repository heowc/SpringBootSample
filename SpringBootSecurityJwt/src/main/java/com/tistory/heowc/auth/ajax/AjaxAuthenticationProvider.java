package com.tistory.heowc.auth.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tistory.heowc.auth.UserDetailsServiceImpl;
import com.tistory.heowc.domain.Member;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getCredentials().toString();
		System.out.println(username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		Member member = new Member(userDetails.getUsername());
		return new AjaxAuthenticationToken(member, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
