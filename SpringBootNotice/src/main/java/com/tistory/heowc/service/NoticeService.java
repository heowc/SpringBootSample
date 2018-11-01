package com.tistory.heowc.service;

import com.tistory.heowc.domain.NoticeWithPage;

public interface NoticeService {

	NoticeWithPage getNotices(Integer page);
}
