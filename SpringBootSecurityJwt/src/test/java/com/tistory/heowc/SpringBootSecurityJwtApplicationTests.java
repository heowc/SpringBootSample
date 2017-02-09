package com.tistory.heowc;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.BeforeClass;
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
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
//  "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTQ4NTkxODAwMH0.cxgLcrlqKo8vWQBhR_2mMhM8NXjqkNj2Y7DPJrG74n0";
// /*-------header-------.-------------------claim-------------------.------------------signature----------------*/

	private static String JWT_TOKEN = null;
	
	private static final String JWT_SECRECT_KEY = "heowc.tistory.com";
	
	private static final LocalDateTime STANDARD_LOCAL = LocalDateTime.of(2017, 2 , 9, 0, 0, 0);
	private static final LocalDateTime EXPIRES_LOCAL  = LocalDateTime.of(2017, 2 , 15, 0, 0, 0);
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	
	private static Date STANDARD_DATE;
	private static Date EXPIRES_DATE;
	
	private static Long DAY = 3600L * 24;
	
	@BeforeClass
	public static void beforeClass_createJwtToken() throws Exception {
		STANDARD_DATE = Date.from(STANDARD_LOCAL.toInstant(ZoneOffset.ofHours(9)));
		EXPIRES_DATE  = Date.from(EXPIRES_LOCAL.toInstant(ZoneOffset.ofHours(9)));
		
		try {
			JWT_TOKEN = JWT.create()
							.withIssuer("wonchul")
							.withIssuedAt(STANDARD_DATE) // 발행일
							.withExpiresAt(EXPIRES_DATE) // 만료일
							.sign(Algorithm.HMAC256(JWT_SECRECT_KEY));

		} catch (JWTCreationException createEx) {
			System.out.println("Create Error");
			createEx.printStackTrace();
		}
	}
	
	@Test
	public void test_decodeJwtToken() throws Exception {
		
		try {
			JWT.decode(JWT_TOKEN);
		} catch (JWTDecodeException decodeEx) {
			System.out.println("Decode Error");
			decodeEx.printStackTrace();
		}
	}
	
	@Test
	public void test_verifyJwtToken() throws Exception {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRECT_KEY))
										.withIssuer("wonchul")
										.acceptIssuedAt(DAY * 1)
//										.acceptExpiresAt(DAY * 10)
										.build();
			
			DecodedJWT jwt = verifier.verify(JWT_TOKEN);
			
			System.out.println("=================== test_verifyJwtToken ===================");
			System.out.println("jwt token         : " + jwt.getToken());
			System.out.println("jwt algorithm     : " + jwt.getAlgorithm());
			System.out.println("jwt claims        : " + jwt.getClaims());
			System.out.println("jwt issuer        : " + jwt.getIssuer());
			System.out.println("jwt issuer date   : " + jwt.getIssuedAt());
			System.out.println("jwt expires date  : " + jwt.getExpiresAt());
			System.out.println("jwt signature     : " + jwt.getSignature());
			System.out.println("jwt type          : " + jwt.getType());
			System.out.println("jwt key id        : " + jwt.getKeyId());
			System.out.println("jwt id            : " + jwt.getId());
			System.out.println("jwt subject       : " + jwt.getSubject());
			System.out.println("jwt content type  : " + jwt.getContentType());
			System.out.println("jwt audience list : " + jwt.getAudience());
			
		} catch (JWTVerificationException verificationEx) {
			System.out.println("Verify Error");
			verificationEx.printStackTrace();
		}
	}
}
