package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Notice;

import java.util.List;

public interface NoticeRepositoryCustom {

	List<Notice> findTopByIdxLimit(Integer top, Integer limit);
}
