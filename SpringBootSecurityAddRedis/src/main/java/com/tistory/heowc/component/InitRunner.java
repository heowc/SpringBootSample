package com.tistory.heowc.component;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitRunner implements ApplicationRunner {

	@Autowired
	private MemberRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) {
		repository.save(new Member("wonchul", passwordEncoder.encode("1234")));
		repository.save(new Member("naeun", passwordEncoder.encode("1234")));
		repository.save(new Member("tistory", passwordEncoder.encode("1234")));
		repository.save(new Member("security", passwordEncoder.encode("1234")));

		for (Member member : repository.findAll()) {
			System.out.println(member.toString());
		}
	}
}
