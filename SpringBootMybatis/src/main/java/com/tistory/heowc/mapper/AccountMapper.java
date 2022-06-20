package com.tistory.heowc.mapper;

import com.tistory.heowc.domain.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {

    String COLUMN = "`IDX`, `NAME`, `LOCAL`";

    @Options(
            useGeneratedKeys = true,
            keyProperty = "idx"
    )
    @Insert("INSERT INTO ACCOUNT VALUES (#{account.idx}, #{account.name}, #{account.local})")
    int insert(@Param("account") Account account);

    @Select("SELECT " + COLUMN + " FROM ACCOUNT WHERE IDX = #{idx}")
    Account findByIdx(@Param("idx") Long idx);

    @Delete("DELETE FROM ACCOUNT WHERE IDX = #{idx}")
    void deleteByIdx(@Param("idx") Long idx);

    @Update("UPDATE ACCOUNT SET NAME = #{account.name} WHERE IDX = #{account.idx}")
    void setFixedNameByIdx(@Param("account") Account account);
}
