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

    @Select("select *from user where account=#{account} and password=#{password}")
    User select(@Param("account") Long account,@Param("password") String password);

    @Select("select *from user where id=#{id}")
    User selectById(Integer id);


    void insert(User user);

    @Delete("delete from user where id=#{id} ;")
    void deleteById(Integer id);

    @Update("update user set username =#{username} where id=#{id} ")
    void updateUsername(@Param("id")Integer id,@Param("username")String username);

    @Update("update user set password =#{password} where id=#{id}")
    void updatePassword(@Param("id")Integer id,@Param("password") String password);
}
