package com.tistory.heowc.mapper;

import com.tistory.heowc.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    String COLUMN = "`IDX`, `NAME`, `LOCAL`";

    @SelectKey(
            statement = "SELECT #{user.idx} FROM DUAL",
            keyProperty = "idx",
            before = false,
            resultType = Long.class
    )
    @Insert("INSERT INTO USER VALUES (#{user.idx}, #{user.name}, #{user.local})")
    int insert(@Param("user") User user);

    @Select("SELECT " + COLUMN + " FROM USER WHERE IDX = #{idx}")
    User findByIdx(@Param("idx") Long idx);

    @Delete("DELETE FROM USER WHERE IDX = #{idx}")
    void deleteByIdx(@Param("idx") Long idx);

    @Update("UPDATE USER SET NAME = #{user.name} WHERE IDX = #{user.idx}")
    void setFixedNameByIdx(@Param("user") User user);
}
