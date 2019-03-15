package com.example.kotlin;

import com.example.onetoone.domain.Market;
import com.example.onetoone.domain.Owner;
import com.example.onetoone.repository.MarketRepository;
import com.example.onetoone.repository.OwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OneToOneTests {

	@Autowired
	MarketRepository marketRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Before
	public void before_init() {
		marketRepository.save(new Market("원철 중화 반점", "서울 구로구"));
		marketRepository.save(new Market("나은 중화 반점", "서울 구로구"));
		marketRepository.save(new Market("구글 중화 반점", "서울 구로구"));

		ownerRepository.save(new Owner("원철"));
		ownerRepository.save(new Owner("나은"));
		ownerRepository.save(new Owner("구글"));

		marketRepository.findAll().forEach(System.out::println);
		ownerRepository.findAll().forEach(System.out::println);
	}

	@Test
	public void test_findOne() {
		System.out.println(marketRepository.findById(1L).orElse(null));
		System.out.println(ownerRepository.findById(1L).orElse(null));
	}
}
