package com.tistory.heowc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;

@Component
public class InitComponent implements CommandLineRunner {

	@Autowired
	MemberRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		Member member = new Member("wonchul", "USER");
		repository.save(member);
	}
}
