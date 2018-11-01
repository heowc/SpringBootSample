package com.tistory.heowc.mapper;

import com.tistory.heowc.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER VALUES (#{user.idx}, #{user.name}, #{user.local})")
    void insert(@Param("user") User user);

    @Select("SELECT * FROM USER WHERE IDX = #{idx}")
    User findByIdx(@Param("idx") Long idx);

    @Delete("DELETE FROM USER WHERE IDX = #{idx}")
    void deleteByIdx(@Param("idx") Long idx);

    @Update("UPDATE USER SET NAME = #{user.name} WHERE IDX = #{user.idx}")
    void setFixedNameByIdx(@Param("user") User user);
}
