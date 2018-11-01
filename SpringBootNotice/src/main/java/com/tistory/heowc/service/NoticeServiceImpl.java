package com.tistory.heowc.service;

import com.tistory.heowc.domain.Notice;
import com.tistory.heowc.domain.NoticeWithPage;
import com.tistory.heowc.domain.Page;
import com.tistory.heowc.repository.NoticeRepository;
import com.tistory.heowc.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;

	private static final int RECODE_SIZE = 10;
	private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);
	
	@Transactional(readOnly=true)
	@Override
	public NoticeWithPage getNotices(Integer pageNo) {
		Page page = PageUtil.of(pageNo, RECODE_SIZE, repository.count());
		List<Notice> noticeList = repository.findTopByIdxLimit(page.getTop(), page.getRecodeSize());
		
		logger.info("Notice 페이지 정보 ====> " + page);
		logger.info("Notice 게시판 정보 ====> " + noticeList);

		return new NoticeWithPage(noticeList, page);
	}
}
