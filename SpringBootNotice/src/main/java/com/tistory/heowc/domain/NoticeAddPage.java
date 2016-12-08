package com.tistory.heowc.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeAddPage {

	private ArrayList<Notice> notices;
	
	private Page page;
	
	protected NoticeAddPage() {}
}
