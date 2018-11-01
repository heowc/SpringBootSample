package com.tistory.heowc;

import com.tistory.heowc.dao.UserDao;
import com.tistory.heowc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void test_insert() {
        // given
        User user = new User(1L, "wonchul", "seoul");

        // when
        userDao.insert(user);

        // then
        User byIdx = userDao.findByIdx(user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    public void test_update() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        userDao.insert(user);

        // when
        user.setIdx(1L);
        user.setName("heowc");
        userDao.setFixedNameByIdx(user);

        // then
        User byIdx = userDao.findByIdx(user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    public void test_delete() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        userDao.insert(user);

        // when
        userDao.deleteByIdx(user.getIdx());

        // then
        User byIdx = userDao.findByIdx(user.getIdx());
        assertThat(byIdx).isNull();
    }

}