package com.tistory.heowc;

import com.tistory.heowc.domain.Account;
import com.tistory.heowc.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountMapperTests {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void test_insert() {
        // given
        Account account = new Account(2L, "wonchul", "seoul");

        // when
        accountMapper.insert(account);

        // then
        Account byIdx = accountMapper.findByIdx(account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", account.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", account.getLocal());
    }

    @Test
    void test_update() {
        // given
        Account account = new Account(1L, "wonchul", "seoul");
        accountMapper.insert(account);

        // when
        account.setIdx(account.getIdx());
        account.setName("heowc");
        accountMapper.setFixedNameByIdx(account);

        // then
        Account byIdx = accountMapper.findByIdx(account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", account.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", account.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", account.getLocal());
    }

    @Test
    void test_delete() {
        // given
        Account account = new Account(1L, "wonchul", "seoul");
        accountMapper.insert(account);

        // when
        accountMapper.deleteByIdx(account.getIdx());

        // then
        Account byIdx = accountMapper.findByIdx(account.getIdx());
        assertThat(byIdx).isNull();
    }
}
