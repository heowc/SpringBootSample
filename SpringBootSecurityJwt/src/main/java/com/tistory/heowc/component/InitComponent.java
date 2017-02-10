package com.tistory.heowc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;

@Component
public class InitComponent implements CommandLineRunner {

	@Autowired
	MemberRepository repository;
	
	@Transactional(readOnly = false)
	@Override
	public void run(String... args) throws Exception {
		Member user = new Member("wonchul", "USER");
		repository.save(user);
		
		Member admin = new Member("naeun", "ADMIN");
		repository.save(admin);
	}
}
