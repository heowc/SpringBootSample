package com.tistory.heowc;

import com.tistory.heowc.dao.AccountDao;
import com.tistory.heowc.domain.Account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountDaoTests {

    @Autowired
    private AccountDao userDao;

    @Test
    void test_insert() {
        // given
        Account account = new Account(2L, "wonchul", "seoul");

        // when
        Long idx = userDao.insert(account);

        // then
        Account byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", account.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", account.getLocal());
    }

    @Test
    void test_update() {
        // given
        Account account = new Account(1L, "wonchul", "seoul");
        Long idx = userDao.insert(account);

        // when
        account.setIdx(idx);
        account.setName("heowc");
        userDao.setFixedNameByIdx(account);

        // then
        Account byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", account.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", account.getLocal());
    }

    @Test
    void test_delete() {
        // given
        Account account = new Account(1L, "wonchul", "seoul");
        Long idx = userDao.insert(account);

        // when
        userDao.deleteByIdx(idx);

        // then
        Account byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).isNull();
    }

}