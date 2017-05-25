package com.tistory.heowc.component;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitComponent implements CommandLineRunner {

	private final MemberRepository repository;
	
	@Transactional(readOnly = false)
	@Override
	public void run(String... args) throws Exception {
		Member user = new Member("wonchul", "USER");
		repository.save(user);
		
		Member admin = new Member("naeun", "ADMIN");
		repository.save(admin);
	}
}
