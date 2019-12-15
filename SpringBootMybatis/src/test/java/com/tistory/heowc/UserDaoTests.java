package com.tistory.heowc;

import com.tistory.heowc.dao.UserDao;
import com.tistory.heowc.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    void test_insert() {
        // given
        User user = new User(2L, "wonchul", "seoul");

        // when
        Long idx = userDao.insert(user);

        // then
        User byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    void test_update() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        Long idx = userDao.insert(user);

        // when
        user.setIdx(idx);
        user.setName("heowc");
        userDao.setFixedNameByIdx(user);

        // then
        User byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    void test_delete() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        Long idx = userDao.insert(user);

        // when
        userDao.deleteByIdx(idx);

        // then
        User byIdx = userDao.findByIdx(idx);
        assertThat(byIdx).isNull();
    }

}