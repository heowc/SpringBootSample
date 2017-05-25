package com.tistory.heowc.auth.jwt;

import com.tistory.heowc.auth.AuthFactory;
import com.tistory.heowc.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtFactory implements AuthFactory {

	private final JwtUtil jwtUtil;
	
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
