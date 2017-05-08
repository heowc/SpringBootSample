package org.springframework.social.naver.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.naver.api.abstracts.UserOperation;
import org.springframework.social.naver.api.model.UserProfile;
import org.springframework.social.naver.api.model.UserProfileResponse;
import org.springframework.social.naver.api.util.NaverApi;
import org.springframework.web.client.RestTemplate;

public class UserOperationImpl implements UserOperation {
	private static final Logger LOG = LoggerFactory.getLogger(UserOperationImpl.class);

	private boolean isAuthorized;
	private UserProfile userProfile;

	public UserOperationImpl(RestTemplate restTemplate, boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
		this.userProfile = restTemplate.getForObject(NaverApi.buildUserUri("/nidlogin/nid/getUserProfile.xml"), UserProfile.class);
	}

	private UserProfileResponse getUserProfile() {
		if (this.isAuthorized) {
			if (!"00".equals(this.userProfile.getResult().getResultcode())) {
				throw new InvalidAuthorizationException("naver", this.userProfile.getResult().getMessage());
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("USER PROFILE RESULT: {}", this.userProfile.toJson(false));
			}
			return this.userProfile.getResponse();
		} else {
			throw new MissingAuthorizationException("naver");
		}
	}

	@Override
	public String getId() {
		return getUserProfile().getId();
	}

	@Override
	public String getNickname() {
		return getUserProfile().getNickname();
	}

	@Override
	public String getName() {
		return getUserProfile().getName();
	}

	@Override
	public String getEmail() {
		return getUserProfile().getEmail();
	}

	@Override
	public String getGender() {
		return getUserProfile().getGender();
	}

	@Override
	public String getAge() {
		return getUserProfile().getAge();
	}

	@Override
	public String getBirthday() {
		return getUserProfile().getBirthday();
	}

	@Override
	public String getProfile_image() {
		return getUserProfile().getProfile_image();
	}
}
