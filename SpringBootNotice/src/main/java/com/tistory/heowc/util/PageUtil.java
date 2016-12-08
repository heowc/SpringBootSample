package com.tistory.heowc.util;

import org.springframework.stereotype.Component;

import com.tistory.heowc.domain.Page;

@Component
public class PageUtil {

	public Page getPage(int pageNo, int recodeSize, long totalSize) {
		Page page = new Page();
		
		page.setPageNo(pageNo);
		page.setRecodeSize(recodeSize);
		page.setTotalSize(totalSize);
		
		page.setTop((pageNo-1)*recodeSize);
		return page;
	}
}
