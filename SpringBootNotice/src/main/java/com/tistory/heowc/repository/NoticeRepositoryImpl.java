package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Notice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Notice> findTopByIdxLimit(Integer top, Integer limit) {
		return em.createQuery("SELECT n FROM Notice n", Notice.class)
										.setFirstResult(top)
										.setMaxResults(limit)
										.getResultList();
	}
}
