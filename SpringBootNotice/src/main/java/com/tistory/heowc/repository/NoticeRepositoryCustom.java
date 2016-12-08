package com.tistory.heowc.repository;

import java.util.ArrayList;

import com.tistory.heowc.domain.Notice;

public interface NoticeRepositoryCustom {

	ArrayList<Notice> findTopByIdxLimit(int top, int limit);
}
