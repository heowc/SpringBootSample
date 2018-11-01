package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoticeWithPage {

	private List<Notice> notices;
	
	private Page page;
	
	protected NoticeWithPage() { }
}
