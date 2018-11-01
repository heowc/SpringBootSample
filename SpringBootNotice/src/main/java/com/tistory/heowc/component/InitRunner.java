package com.tistory.heowc.component;

import com.tistory.heowc.domain.Notice;
import com.tistory.heowc.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InitRunner implements ApplicationRunner {
	
	@Autowired NoticeRepository repository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 50; i++) {
			repository.save(new Notice((long) i, "title " + i, "content " + i));
		}
	}
}
