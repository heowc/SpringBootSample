package com.tistory.heowc.auth.jwt;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.UserDetailsImpl;
import com.tistory.heowc.domain.Member;
import com.tistory.heowc.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final ObjectMapper objectMapper;

	private final JwtUtil jwtUtil;
	private final JwtFactory jwtFactory;
	
	@Override
	public UserDetailsImpl loadUserByUsername(String token) {
		System.out.println(token);
		if(!jwtFactory.verifyToken(token)) {
			throw new BadCredentialsException("Not used Token");
		}
		
		JWT jwt = jwtUtil.tokenToJwt(token);
		Member member = getStringToMember(jwt.getClaim("member").asString());
		
		if(member == null) {
			throw new BadCredentialsException("Not used Token");
		}
		
		return new UserDetailsImpl(member, AuthorityUtils.createAuthorityList(member.getRole()));
	}
	
	private Member getStringToMember(String memberStr) {
		try {
			return objectMapper.readValue(memberStr, Member.class);
		} catch (IOException e) {
			return null;
		}
	}
}
