package com.tistory.heowc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.heowc.domain.Notice;
import com.tistory.heowc.repository.NoticeRepository;

@Component
public class InitRunner implements CommandLineRunner {
	
	@Autowired NoticeRepository repository;
	
	@Transactional(readOnly=false)
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 50; i++) {
			repository.save(new Notice(i, "title " + i, "content " + i));
		}
	}
}
