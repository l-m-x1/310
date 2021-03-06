package com.space.mapper;

import com.space.pojo.Info;
import com.space.pojo.Mood;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MoodMapper {
    @Insert("insert into mood values (null,#{uid},#{type},#{content})")
    void insert(Mood mood);

    @Delete("delete from mood where id=#{id}")
    void delete(Integer id);

    @Update("update mood set type=#{type},content=#{content} where id=#{id}")
    void update(Mood mood);

    @Select("select *from mood where uid=#{uid}")
    List<Mood> selectByUid(Integer uid);

    @Select("select *from mood where id=#{id}")
    Mood selectById(Integer id);
}
