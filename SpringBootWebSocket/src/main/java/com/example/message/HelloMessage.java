package com.example.message;

import java.time.LocalDateTime;

public class HelloMessage {

	private String name;
	private String contents;
	private LocalDateTime sendDate;

	protected HelloMessage() { }

	public HelloMessage(String name, String contents, LocalDateTime sendDate) {
		this.name = name;
		this.contents = contents;
		this.sendDate = sendDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
}

