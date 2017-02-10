package com.tistory.heowc.auth;

public interface AuthFactory {

	String createToken(String info);
	
	Boolean verifyToken(String token);
	
	String refreshToken(String token);
}
