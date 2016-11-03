package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Person;

@Service
public class PersonService {

	@Autowired
	private EntityManager em;
	
	private static final String findAll = "SELECT p FROM Person p";
	public List<Person> findAll(){
		return em.createQuery(findAll, Person.class).getResultList();
	}
	
	/*
	 * 별칭 필수
	 * DTO 조회 : SELECT new com....Dto
	 * Paging   : setFirstResult , setMaxResults 
	 */
	
	public List<Person> findByFirstName(String firstName) {
		return em.createNamedQuery("Person.findByFirstName", Person.class)
					.setParameter("firstName", firstName)
					.getResultList();
	}
}
