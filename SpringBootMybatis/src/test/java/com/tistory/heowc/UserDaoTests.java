package com.tistory.heowc;

import com.tistory.heowc.dao.UserDao;
import com.tistory.heowc.domain.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTests {

    @Autowired
    UserDao userDao;

    @Test
    public void test_1insert() {
        userDao.insert(new User(1L, "wonchul", "seoul"));
    }

    @Test
    public void test_2select() {
        System.out.println(userDao.findByIdx(1L));
    }

    @Test
    public void test_3update() {
        User user = new User();
        user.setIdx(1L);
        user.setName("heowc");

        userDao.setFixedNameByIdx(user);
    }

    @Test
    public void test_4select() {
        System.out.println(userDao.findByIdx(1L));
    }

    @Test
    public void test_5delete() {
        userDao.deleteByIdx( 1L);
    }
}