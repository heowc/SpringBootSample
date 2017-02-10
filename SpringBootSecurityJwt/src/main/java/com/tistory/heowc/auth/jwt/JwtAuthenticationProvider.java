package com.tistory.heowc.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.tistory.heowc.domain.Member;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		
		JWT jwt = jwtUtil.tokenToJwt(token);
		
		return new JwtAuthenticationToken(jwt.getClaim("member").as(Member.class), AuthorityUtils.createAuthorityList(jwt.getClaim("role").asString()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
