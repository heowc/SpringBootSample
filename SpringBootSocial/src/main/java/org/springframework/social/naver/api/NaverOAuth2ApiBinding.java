package org.springframework.social.naver.api;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.social.naver.api.abstracts.UserOperation;
import org.springframework.social.naver.api.impl.UserOperationImpl;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.HttpRequestDecorator;

import java.io.IOException;
import java.util.Arrays;

public final class NaverOAuth2ApiBinding extends AbstractOAuth2ApiBinding implements Naver {
	private UserOperation userOperation;

	public NaverOAuth2ApiBinding(final String accessToken) {
		super(accessToken);
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));

		// set interceptors
		getRestTemplate().setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor[]{new AdminKeyHeaderOAuth2RequestInterceptor(accessToken)}));

		// set xml->class http message converters
		getRestTemplate().setMessageConverters(Arrays.asList(new HttpMessageConverter<?>[]{new Jaxb2RootElementHttpMessageConverter()}));

		// init api operations
		userOperation = new UserOperationImpl(getRestTemplate(), isAuthorized());
	}

	@Override
	public void setRequestFactory(final ClientHttpRequestFactory requestFactory) {
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
	}

	public UserOperation userOperation() {
		return userOperation;
	}

	/**
	 * rest template 에 설정할 interceptor
	 */
	class AdminKeyHeaderOAuth2RequestInterceptor implements ClientHttpRequestInterceptor {
		private final String accessToken;

		public AdminKeyHeaderOAuth2RequestInterceptor(final String accessToken) {
			this.accessToken = accessToken;
		}

		public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
			HttpRequest protectedResourceRequest = new HttpRequestDecorator(request);
			protectedResourceRequest.getHeaders().set("Host", "apis.naver.com");
			protectedResourceRequest.getHeaders().set("Pragma", "no-cache");
			protectedResourceRequest.getHeaders().set("Accept", "text/xml");
			protectedResourceRequest.getHeaders().set("Authorization", "Bearer " + accessToken);
			return execution.execute(protectedResourceRequest, body);
		}
	}
}
