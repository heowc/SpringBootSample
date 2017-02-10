package com.tistory.heowc.auth.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tistory.heowc.util.DateUtil;

@Component
public class JwtUtil {

	@Autowired
	private DateUtil dateUtil;
	
	public String createToken() {
		return createToken(dateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMMIT));
	}
	
	private String createToken(Date date) {
		try {
			return JWT.create()
					.withIssuer(JwtInfo.ISSUER)
					.withExpiresAt(date)
					.sign(JwtInfo.getAlgorithm());
		} catch (JWTCreationException createEx) {
			return null;
		}
	}
	
	public Boolean verify(String token) {
		try {
			JWTVerifier verifier = JWT.require(JwtInfo.getAlgorithm()).build();
			verifier.verify(token);
			
			return Boolean.TRUE;
		} catch (JWTVerificationException verifyEx) {
			return Boolean.FALSE;
		}
	}

	public String refreshToken(String token) {
		try {
			JWT jwt = JWT.decode(token);
			return null;
		} catch (JWTDecodeException decodeEx) {
			return null;
		}
	}
	
	public JWT tokenToJwt(String token) {
		try {
			return JWT.decode(token);
		} catch (JWTDecodeException decodeEx) {
			return null;
		}
	}
}
