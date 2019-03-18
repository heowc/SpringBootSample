package com.tistory.heowc.domain;

public class ErrorInfo {

	private final String url;
	private final String message;

	public ErrorInfo(String url, Exception ex) {
		this.url = url;
		this.message = ex.getMessage();
	}

	public String getUrl() {
		return url;
	}

	public String getMessage() {
		return message;
	}
}