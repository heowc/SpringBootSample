package com.tistory.heowc.auth.jwt.matcher;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class SkipPathRequestMatcher implements RequestMatcher {

	private OrRequestMatcher skipRequestMatcher;
	
	public SkipPathRequestMatcher(List<String> skipPathList) {
		if(!skipPathList.isEmpty()) {
			List<RequestMatcher> requestMatcherList = skipPathList.stream()
																	.map(AntPathRequestMatcher::new)
																	.collect(Collectors.toList());
			skipRequestMatcher = new OrRequestMatcher(requestMatcherList);
		}
	}
	
	@Override
	public boolean matches(HttpServletRequest request) {
		return !skipRequestMatcher.matches(request);
	}
}
