package com.tistory.heowc.service;

import com.tistory.heowc.domain.Notice;
import com.tistory.heowc.domain.NoticeAddPage;
import com.tistory.heowc.domain.Page;
import com.tistory.heowc.repository.NoticeRepository;
import com.tistory.heowc.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;
	private final PageUtil pageUtil;
	
	private static final int RECODE_SIZE = 10;
	private static final Logger logger = Logger.getLogger(NoticeService.class);
	
	@Transactional(readOnly=true)
	@Override
	public NoticeAddPage getNotices(int pageNo) {
		Page page = pageUtil.getPage(pageNo, RECODE_SIZE, repository.count());
		ArrayList<Notice> notices = repository.findTopByIdxLimit(page.getTop(), page.getRecodeSize());
		
		logger.info("Notice 페이지 정보 ====> " + page);
		logger.info("Notice 게시판 정보 ====> " + notices);
		
		return new NoticeAddPage(notices, page);
	}
}
