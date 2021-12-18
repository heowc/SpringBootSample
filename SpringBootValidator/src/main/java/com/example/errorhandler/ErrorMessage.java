package com.example.errorhandler;

public class ErrorMessage {

	private final String field;
	private final String message;

	public static ErrorMessage of(String field, String message) {
		return new ErrorMessage(field, message);
	}

	protected ErrorMessage(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}

