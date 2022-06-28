package com.space.mapper;

import com.space.pojo.Diary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DiaryMapper {
    @Insert("insert into diary VALUES (null,#{time},#{uid},#{content})")
    public void insert( Diary diary);
    @Delete("delete from diary where id=#{id}")
    public void delete(Integer id);
    @Update("update diary set time=#{time},content=#{content} where id=#{id}")
    public void update(Diary diary);
    @Select("select *from diary where uid=#{uid}")
    public List<Diary> selectByUid(Integer uid);
    @Select("select *from diary where id=#{id}")
    public Diary selectById(Integer id);
}
