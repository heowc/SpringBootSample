package com.tistory.heowc.dao;

import com.tistory.heowc.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    @Autowired SqlSession sqlSession;

    public void insert(User user) {
        sqlSession.insert("UserMapper.insert", user);
    }

    public List<User> findByIdx(Long idx) {
        return sqlSession.selectList("UserMapper.findByIdx", idx);
    }

    public void deleteByIdx(Long idx) {
        sqlSession.delete("UserMapper.deleteByIdx", idx);
    }

    public void setFixedNameByIdx(User user) {
        sqlSession.update("UserMapper.setFixedNameByIdx", user);
    }
}
