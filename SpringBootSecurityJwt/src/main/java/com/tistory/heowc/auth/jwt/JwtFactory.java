package com.tistory.heowc.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tistory.heowc.auth.AuthFactory;

@Component
public class JwtFactory implements AuthFactory {

	@Autowired JwtUtil jwtUtil;
	
	@Override
	public String createToken(String info) {
		return jwtUtil.createToken(info);
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
