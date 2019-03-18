package com.example.java;

import com.example.java.onetoone.domain.Market;
import com.example.java.onetoone.domain.Owner;
import com.example.java.onetoone.repository.MarketRepository;
import com.example.java.onetoone.repository.OwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaApplication.class)
@Transactional
public class OneToOneTests {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Before
    public void before_init() {
        Market wonchulMarket = marketRepository.save(new Market("원철 중화 반점", "서울 구로구"));
        Market naeunMarket = marketRepository.save(new Market("나은 중화 반점", "서울 구로구"));
        Market googleMarket = marketRepository.save(new Market("구글 중화 반점", "서울 구로구"));

        Owner wonchul = ownerRepository.save(new Owner("원철"));
        wonchul.setMarket(wonchulMarket);
        wonchulMarket.setOwner(wonchul);

        Owner naeun = ownerRepository.save(new Owner("나은"));
        naeun.setMarket(naeunMarket);
        naeunMarket.setOwner(naeun);

        Owner google = ownerRepository.save(new Owner("구글"));
        google.setMarket(googleMarket);
        googleMarket.setOwner(google);

        marketRepository.findAll().forEach(System.out::println);
        ownerRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void test_findOne() {
        System.out.println(marketRepository.findById(1L).orElse(null));
        System.out.println(ownerRepository.findById(1L).orElse(null));
    }
}
