package com.tistory.heowc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExtensionException extends RuntimeException {

	private String message;

	public ExtensionException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
