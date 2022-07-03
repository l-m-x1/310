package com.space.mapper;

import com.space.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select *from user")
    List<User> selectAll();

    @Select("select *from user where id=#{id} and password=#{password}")
    User select(@Param("id") Integer id,@Param("password") String password);

    @Select("select *from user where id=#{id}")
    User selectById(Integer id);


    void insert(User user);

    @Delete("delete from user where id=#{id} ;")
    void deleteById(Integer id);

    @Update("update user set username =#{username} where id=#{id} ")
    void updateUsername(@Param("id")Integer id,@Param("username")String username);

    @Update("update user set password =#{password} where id=#{id}")
    void updatePassword(@Param("id")Integer id,@Param("password") String password);

    @Select("select max(id) from user")
    Integer getMaxId();

    @Update("update user set avatar=#{avatar} where id=#{id}")
    void updateAvatar(@Param("id")Integer id,@Param("avatar") String avatar);
}
