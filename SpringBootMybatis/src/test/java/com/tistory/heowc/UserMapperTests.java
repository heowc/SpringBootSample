package com.tistory.heowc;

import com.tistory.heowc.domain.User;
import com.tistory.heowc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test_insert() {
        // given
        User user = new User(2L, "wonchul", "seoul");

        // when
        userMapper.insert(user);

        // then
        User byIdx = userMapper.findByIdx(user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    void test_update() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        userMapper.insert(user);

        // when
        user.setIdx(user.getIdx());
        user.setName("heowc");
        userMapper.setFixedNameByIdx(user);

        // then
        User byIdx = userMapper.findByIdx(user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("idx", user.getIdx());
        assertThat(byIdx).hasFieldOrPropertyWithValue("name", user.getName());
        assertThat(byIdx).hasFieldOrPropertyWithValue("local", user.getLocal());
    }

    @Test
    void test_delete() {
        // given
        User user = new User(1L, "wonchul", "seoul");
        userMapper.insert(user);

        // when
        userMapper.deleteByIdx(user.getIdx());

        // then
        User byIdx = userMapper.findByIdx(user.getIdx());
        assertThat(byIdx).isNull();
    }
}
