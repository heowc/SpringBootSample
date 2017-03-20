package com.tistory.heowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.heowc.domain.NoticeAddPage;
import com.tistory.heowc.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired NoticeService service;
	
	@GetMapping
	public NoticeAddPage getNotices(
			@RequestParam(value = "page", required=false, defaultValue="1") int pageNo) {
		if(pageNo > 0){
			return service.getNotices(pageNo);
		} else {
			throw new IllegalArgumentException("Page must be 0 or greater.");
		}
	}
}
