package com.tistory.heowc.auth;

public interface AuthFactory {

	String createToken();
	
	Boolean verifyToken(String token);
	
	String refreshToken(String token);
}
