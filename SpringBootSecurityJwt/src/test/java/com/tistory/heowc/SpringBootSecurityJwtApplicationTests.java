package com.tistory.heowc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootSecurityJwtApplication.class);

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

//  "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTQ4NTkxODAwMH0.cxgLcrlqKo8vWQBhR_2mMhM8NXjqkNj2Y7DPJrG74n0";
// /*-------header-------.-------------------claim-------------------.------------------signature----------------*/

	private static String JWT_TOKEN = null;

	private static final String TOKEN_KEY = "heowc.github.io";

	private static final LocalDateTime EXPIRES_LOCAL = LocalDateTime.now();

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

	private static final Date EXPIRES_DATE = Date.from(EXPIRES_LOCAL.toInstant(ZoneOffset.ofHours(9)));

	private static final Long DAY = 3600L * 24;

	@BeforeClass
	public static void beforeClass_createJwtToken() throws Exception {
		try {
			JWT_TOKEN = JWT.create()
					.withIssuer("wonchul")
					.withExpiresAt(EXPIRES_DATE) // 만료일
					.sign(Algorithm.HMAC256(TOKEN_KEY));

		} catch (JWTCreationException createEx) {
			logger.info("Create Error");
			createEx.printStackTrace();
		}
	}

	@Test
	public void test_decodeJwtToken() {
		try {
			JWT.decode(JWT_TOKEN);
		} catch (JWTDecodeException decodeEx) {
			logger.info("Decode Error");
			decodeEx.printStackTrace();
		}
	}

	@Test
	public void test_verifyJwtToken() throws Exception {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY))
					.withIssuer("wonchul")
					.acceptExpiresAt(DAY * 4) // 만료일 -4일
					.build();

			DecodedJWT jwt = verifier.verify(JWT_TOKEN);

			logger.info("=================== test_verifyJwtToken ===================");
			logger.info("jwt token         : " + jwt.getToken());
			logger.info("jwt algorithm     : " + jwt.getAlgorithm());
			logger.info("jwt claims        : " + jwt.getClaims());
			logger.info("jwt issuer        : " + jwt.getIssuer());
			logger.info("jwt issuer date   : " + jwt.getIssuedAt());
			logger.info("jwt expires date  : " + jwt.getExpiresAt());
			logger.info("jwt signature     : " + jwt.getSignature());
			logger.info("jwt type          : " + jwt.getType());
			logger.info("jwt key id        : " + jwt.getKeyId());
			logger.info("jwt id            : " + jwt.getId());
			logger.info("jwt subject       : " + jwt.getSubject());
			logger.info("jwt content type  : " + jwt.getContentType());
			logger.info("jwt audience list : " + jwt.getAudience());

		} catch (JWTVerificationException verificationEx) {
			logger.info("Verify Error");
			verificationEx.printStackTrace();
		}
	}
}
