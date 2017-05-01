package com.tistory.heowc.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Base implements Serializable {

	private long index;
	private String content;
	
	public Base() {}
	
	public Base(long index, String content) {
		this.index = index;
		this.content = content;
	}
	
	public void setIndex(long index) {
		this.index = index;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public long getIndex() {
		return index;
	}
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return "Base [index=" + index + ", content=" + content + "]";
	}
}
