package com.tistory.heowc.dao;

import com.tistory.heowc.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final SqlSession sqlSession;

    private static final String NAMESPACE = "com.tistory.heowc.dao.User.";

    public Long insert(User user) {
        boolean isInserted = sqlSession.insert(NAMESPACE + "insert", user) == 1;

        if (isInserted) {
            return user.getIdx();
        } else {
            return null;
        }
    }

    public User findByIdx(Long idx) {
        return sqlSession.selectOne(NAMESPACE + "findByIdx", idx);
    }

    public void deleteByIdx(Long idx) {
        sqlSession.delete(NAMESPACE + "deleteByIdx", idx);
    }

    public void setFixedNameByIdx(User user) {
        sqlSession.update(NAMESPACE + "setFixedNameByIdx", user);
    }
}
