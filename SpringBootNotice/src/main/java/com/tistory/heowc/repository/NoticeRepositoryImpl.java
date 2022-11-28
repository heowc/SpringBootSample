package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Notice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
