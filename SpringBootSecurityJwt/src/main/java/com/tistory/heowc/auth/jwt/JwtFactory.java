package com.tistory.heowc.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tistory.heowc.auth.AuthFactory;

@Component
public class JwtFactory implements AuthFactory {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public String createToken() {
		return jwtUtil.createToken();
	}
	
	@Override
	public Boolean verifyToken(String token) {
		return jwtUtil.verify(token);
	}

	@Override
	public String refreshToken(String token) {
		return jwtUtil.refreshToken(token);
	}
}
