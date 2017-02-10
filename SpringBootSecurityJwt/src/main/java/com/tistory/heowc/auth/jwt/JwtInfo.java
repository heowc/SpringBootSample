package com.tistory.heowc.auth.jwt;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtInfo {

	public static String HEADER_NAME = "jwt-header";
	
	public static String ISSUER = "wonchul";
	
	public static String TOKEN_KEY = "heowc.tistroy.com";
	
	public static Long EXPIRES_LIMMIT = 3 * 1L;
	
	public static Algorithm getAlgorithm() {
		try {
			return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			return Algorithm.none();
		}
	}
}
