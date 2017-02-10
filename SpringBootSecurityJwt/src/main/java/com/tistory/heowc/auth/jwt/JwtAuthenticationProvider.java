package com.tistory.heowc.auth.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.domain.Member;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		JWT jwt = jwtUtil.tokenToJwt(token);
		Member member = getStringToMember(jwt.getClaim("member").asString());

		if(member != null) {
			System.out.println(member);
			System.out.println(member.getId() + "/" + member.getRole());
			return new JwtAuthenticationToken(member, AuthorityUtils.createAuthorityList(member.getRole()));
		} else {
			throw new BadCredentialsException("Not used Token");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	private Member getStringToMember(String memberStr) {
		try {
			return objectMapper.readValue(memberStr, Member.class);
		} catch (IOException e) {
			return null;
		}
	}
}
