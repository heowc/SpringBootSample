package com.example.kotlin

import com.example.kotlin.account.Account
import com.example.kotlin.config.ConfigurableConfig
import com.example.kotlin.config.SecurityConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import org.assertj.core.api.Assertions.assertThat

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
@Import(ConfigurableConfig::class, SecurityConfig::class)
class AccountTest {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Test
    fun test_Account_Configurable_검사() {
        // given
        val account = Account("heowc", "1234")
        entityManager.persist(account)
        entityManager.flush()
        entityManager.clear()

        // when
        val heowc = entityManager.find(Account::class.java, account.id)
        heowc.resetPassword()
        entityManager.flush()
        entityManager.clear()

        // then
        val result = entityManager.find(Account::class.java, account.id)
        assertThat(result.id).isEqualTo(account.id)
        assertThat(result.password).isNotEqualTo(account.password)
    }
}
