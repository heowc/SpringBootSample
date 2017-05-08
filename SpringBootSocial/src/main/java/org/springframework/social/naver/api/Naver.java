package org.springframework.social.naver.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.naver.api.abstracts.UserOperation;

public interface Naver extends ApiBinding {
	UserOperation userOperation();
}
