package com.example.kotlin

import com.example.kotlin.onetoone.domain.Market
import com.example.kotlin.onetoone.domain.Owner
import com.example.kotlin.onetoone.repository.MarketRepository
import com.example.kotlin.onetoone.repository.OwnerRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
open class OneToOneTests {

    @Autowired
    lateinit var marketRepository: MarketRepository

    @Autowired
    lateinit var ownerRepository: OwnerRepository

    @Before
    fun before_init() {
        val wonchulMarket = marketRepository.save(Market(name = "원철 중화 반점", location = "서울 구로구"))
        val naeunMarket = marketRepository.save(Market(name = "나은 중화 반점",  location = "서울 구로구"))
        val googleMarket = marketRepository.save(Market(name = "구글 중화 반점", location = "서울 구로구"))

        val wonchul = ownerRepository.save(Owner(name = "원철"))
        wonchul.market = wonchulMarket
        wonchulMarket.owner = wonchul

        val naeun = ownerRepository.save(Owner(name = "나은"))
        naeun.market = naeunMarket
        naeunMarket.owner = naeun

        val google = ownerRepository.save(Owner(name = "구글"))
        google.market = googleMarket
        googleMarket.owner = google


        marketRepository.findAll().forEach { println(it) }
        ownerRepository.findAll().forEach { println(it) }
    }

    @Test
    fun test_findOne() {
        println(marketRepository.findById(1L).orElse(null))
        println(ownerRepository.findById(1L).orElse(null))
    }
}
