package com.tistory.heowc.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tistory.heowc.domain.Notice;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

	@PersistenceContext EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Notice> findTopByIdxLimit(int top, int limit) {
		return (ArrayList<Notice>) em.createQuery("SELECT n FROM Notice n")
										.setFirstResult(top)
										.setMaxResults(limit)
										.getResultList();
	}
}
