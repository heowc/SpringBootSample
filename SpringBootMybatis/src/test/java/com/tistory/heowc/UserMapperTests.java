package com.tistory.heowc;

import com.tistory.heowc.mapper.UserMapper;
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
public class UserMapperTests {

    @Autowired UserMapper userMapper ;

    @Test
    public void test_1insert() {
        userMapper.insert(1L, "wonchul", "seoul");
    }

    @Test
    public void test_2select() {
        System.out.println(userMapper.findByIdx(1L));
    }

    @Test
    public void test_3update() {
        userMapper.setFixedNameByIdx("heowc", 1L);
    }

    @Test
    public void test_4select() {
        System.out.println(userMapper.findByIdx(1L));
    }

    @Test
    public void test_5delete() {
        userMapper.deleteByIdx( 1L);
    }
}
