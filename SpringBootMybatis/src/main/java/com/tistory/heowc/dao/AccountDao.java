package com.tistory.heowc.dao;

import com.tistory.heowc.domain.Account;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDao {

    private final SqlSession sqlSession;

    private static final String NAMESPACE = "com.tistory.heowc.dao.Account.";

    public Long insert(Account account) {
        boolean inserted = sqlSession.insert(NAMESPACE + "insert", account) == 1;

        if (inserted) {
            return account.getIdx();
        } else {
            return null;
        }
    }

    public Account findByIdx(Long idx) {
        return sqlSession.selectOne(NAMESPACE + "findByIdx", idx);
    }

    public void deleteByIdx(Long idx) {
        sqlSession.delete(NAMESPACE + "deleteByIdx", idx);
    }

    public void setFixedNameByIdx(Account account) {
        sqlSession.update(NAMESPACE + "setFixedNameByIdx", account);
    }
}
