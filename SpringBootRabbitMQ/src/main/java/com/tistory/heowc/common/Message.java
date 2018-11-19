package com.tistory.heowc.common;

import java.io.Serializable;

public class Message implements Serializable {

	private Long index;
	private String content;
	
	public Message() {}

	public static Message of(Long index, String content) {
		return new Message(index, content);
	}

	public Message(Long index, String content) {
		this.index = index;
		this.content = content;
	}
	
	public void setIndex(long index) {
		this.index = index;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getIndex() {
		return index;
	}
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return "Message [index=" + index + ", content=" + content + "]";
	}
}
