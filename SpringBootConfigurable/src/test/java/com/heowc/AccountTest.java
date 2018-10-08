package com.heowc;

import com.heowc.account.Account;
import com.heowc.config.ConfigurableConfig;
import com.heowc.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Import({ConfigurableConfig.class, SecurityConfig.class})
public class AccountTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test_Account_Configurable_검사() {
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
