package com.tistory.heowc;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {
	
	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
	private static final String JWT_TOKEN =
           "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTQ4NTkxODAwMH0.cxgLcrlqKo8vWQBhR_2mMhM8NXjqkNj2Y7DPJrG74n0";
          /*-------header-------.-------------------claim-------------------.------------------signature----------------*/
	
	private static final String JWT_SECRECT_KEY = "heowc.tistory.com";
	
	private static final LocalDateTime STANDARD_DATE = LocalDateTime.of(2017, 2 , 1, 12, 0, 0, 0);
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
	@Test
	public void test_createJwtToken() throws Exception {
		
		try {
			String token = JWT.create()
					.withIssuer("auth0")
					.withExpiresAt(Date.from(STANDARD_DATE.toInstant(ZoneOffset.ofHours(9))))
					.sign(Algorithm.HMAC256(JWT_SECRECT_KEY));
			
			assertThat(token).isEqualTo(JWT_TOKEN);
		} catch (JWTCreationException createEx) {
			System.out.println("Create Error");
			createEx.printStackTrace();
		}
	}
	
	@Test
	public void test_decodeJwtToken() throws Exception {
		
		try {
			JWT jwt = JWT.decode(JWT_TOKEN);
		} catch (JWTDecodeException decodeEx) {
			System.out.println("Decode Error");
			decodeEx.printStackTrace();
		}
	}
	
	@Test
	public void test_verifyJwtToken() throws Exception {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRECT_KEY))
										.withIssuer("auth0")
										.build();

			DecodedJWT jwt = verifier.verify(JWT_TOKEN);
			
			System.out.println("=================== test_verifyJwtToken ===================");
			System.out.println("jwt token         : " + jwt.getToken());
			System.out.println("jwt algorithm     : " + jwt.getAlgorithm());
			System.out.println("jwt claims        : " + jwt.getClaims());
			System.out.println("jwt issuer        : " + jwt.getIssuer());
			System.out.println("jwt expires       : " + jwt.getExpiresAt());
			System.out.println("jwt signature     : " + jwt.getSignature());
			System.out.println("jwt type          : " + jwt.getType());
			System.out.println("jwt key id        : " + jwt.getKeyId());
			System.out.println("jwt id            : " + jwt.getId());
			System.out.println("jwt subject       : " + jwt.getSubject());
			System.out.println("jwt content type  : " + jwt.getContentType());
			System.out.println("jwt audience list : " + jwt.getAudience());
			
		} catch (JWTVerificationException verificationEx) {
			System.out.println("Verify Error");
			System.out.println("com.auth0.jwt.exceptions.InvalidClaimException: The Token has expired on Wed Feb 01 12:00:00 KST 2017.");
			verificationEx.printStackTrace();
		}
	}
}
