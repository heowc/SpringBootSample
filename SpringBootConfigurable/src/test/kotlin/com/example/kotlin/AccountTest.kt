package com.example.kotlin

import com.example.kotlin.account.Account
import com.example.kotlin.config.ConfigurableConfig
import com.example.kotlin.config.SecurityConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

@SpringBootTest
@Transactional
@Import(ConfigurableConfig::class, SecurityConfig::class)
class AccountTest {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Test
    fun `Account 비밀번호 리셋 테스트`() {
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
