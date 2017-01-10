package com.tistory.heowc.domain;

public class ErrorInfo {
	
	public final String url;
	public final String ex;

	public ErrorInfo(String url, Exception ex) {
		this.url = url;
		this.ex = ex.getLocalizedMessage();
	}
}