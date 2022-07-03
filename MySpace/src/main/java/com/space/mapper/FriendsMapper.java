package com.space.mapper;

import com.space.pojo.Friends;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FriendsMapper {
    @Insert("insert into friends values (#{id},#{fid},#{access})")
    void insert(Friends friends);

    @Delete("delete from friends where id=#{id} and fid=#{fid}")
    void delete(Friends friends);

    @Update("update friends set access=#{access},valid=#{valid} where id=#{id} and fid=#{fid}")
    void update(Friends friends);

    @Select("select *from friends where id=#{id}")
    List<Friends> selectById(Integer id);
}
