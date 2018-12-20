package com.tistory.heowc.dao;

import com.tistory.heowc.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final SqlSession sqlSession;

    public Long insert(User user) {
        boolean isInserted = sqlSession.insert("UserMapper.insert", user) == 1 ? true : false;

        if (isInserted) {
            return user.getIdx();
        } else {
            return null;
        }
    }

    public User findByIdx(Long idx) {
        return sqlSession.selectOne("UserMapper.findByIdx", idx);
    }

    public void deleteByIdx(Long idx) {
        sqlSession.delete("UserMapper.deleteByIdx", idx);
    }

    public void setFixedNameByIdx(User user) {
        sqlSession.update("UserMapper.setFixedNameByIdx", user);
    }
}
