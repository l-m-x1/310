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
    public void insert(Mood mood);
    @Delete("delete from mood where id=#{id}")
    public void delete(Integer id);
    @Update("update mood set type=#{type},content=#{content} where id=#{id}")
    public void update(Mood mood);
    @Select("select *from mood where uid=#{uid}")
    public List<Mood> selectByUid(Integer uid);
    @Select("select *from mood where id=#{id}")
    public Mood selectById(Integer id);
}
