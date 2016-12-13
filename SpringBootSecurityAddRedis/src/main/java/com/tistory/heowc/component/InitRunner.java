package com.tistory.heowc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;

@Component
public class InitRunner implements CommandLineRunner {

	@Autowired
	MemberRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Member("wonchul", passwordEncoder.encode("1234")));
		repository.save(new Member("naeun", passwordEncoder.encode("1234")));
		repository.save(new Member("tistory", passwordEncoder.encode("1234")));
		repository.save(new Member("security", passwordEncoder.encode("1234")));
		
		for( Member member : repository.findAll()) {
			System.out.println(member.toString());
		}
	}
}
