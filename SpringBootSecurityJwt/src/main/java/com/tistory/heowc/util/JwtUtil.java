package com.tistory.heowc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tistory.heowc.auth.jwt.JwtInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	private final DateUtil dateUtil;
	
	public String createToken(String member) {
		return createToken(member, dateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMMIT));
	}
	
	private String createToken(String member, Date date) {
		try {
			return JWT.create()
					.withIssuer(JwtInfo.ISSUER)
					.withClaim("member", member)
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

	public String refreshToken(String member) {
		return createToken(member, dateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMMIT));
	}
	
	public JWT tokenToJwt(String token) {
		try {
			return JWT.decode(token);
		} catch (JWTDecodeException decodeEx) {
			return null;
		}
	}
}
