package com.example.java;

import com.example.java.account.Account;
import com.example.java.config.ConfigurableConfig;
import com.example.java.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Import({ConfigurableConfig.class, SecurityConfig.class})
public class AccountTest {

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("Account 비밀번호 리셋 테스트")
    @Test
    public void test() {
        // given
        Account account = new Account("heowc", "1234");
        entityManager.persist(account);
        entityManager.flush();
        entityManager.clear();

        // when
        Account heowc = entityManager.find(Account.class, account.getId());
        heowc.resetPassword();
        entityManager.flush();
        entityManager.clear();

        // then
        Account result = entityManager.find(Account.class, account.getId());
        assertThat(result.getId()).isEqualTo(account.getId());
        assertThat(result.getPassword()).isNotEqualTo(account.getPassword());
    }
}
