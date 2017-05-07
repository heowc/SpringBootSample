package java.org.springframework.social.naver.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class NaverOAuth2Template extends OAuth2Template {
	private static final Logger LOG = LoggerFactory.getLogger(NaverOAuth2Template.class);

	public NaverOAuth2Template(final String clientId, final String clientSecret) {
		super(
				clientId, clientSecret,
				"https://nid.naver.com/oauth2.0/authorize",
				"https://nid.naver.com/oauth2.0/token"
		);
		setUseParametersForClientAuthentication(true);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected AccessGrant postForAccessGrant(final String accessTokenUrl, final MultiValueMap<String, String> parameters) {
		final List<MediaType> mediaType = new ArrayList<MediaType>();
		mediaType.add(MediaType.ALL);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(mediaType);

		final HttpEntity<?> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

		if (LOG.isDebugEnabled()) {
			LOG.debug("REQUEST HEADERS: {}", requestEntity.getHeaders().toString());
			LOG.debug("REQUEST BODY: {}", requestEntity.getBody().toString());
		}

		final LinkedHashMap<String, Object> response = getRestTemplate().postForObject(accessTokenUrl, requestEntity, LinkedHashMap.class);

		if (LOG.isDebugEnabled()) {
			LOG.debug("RESPONSE DATA: {}", response);
		}

		return new AccessGrant(
				(String) response.get("access_token"),
				(String) response.get("scope"), // null
				(String) response.get("refresh_token"),
				Long.valueOf((String) response.get("expires_in"))
		);
	}
}
