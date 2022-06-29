package com.space.mapper;

import com.space.pojo.Diary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DiaryMapper {
    @Insert("insert into diary VALUES (null,#{uid},#{time},#{content})")
    void insert( Diary diary);
    @Delete("delete from diary where id=#{id}")
    void delete(Integer id);
    @Update("update diary set time=#{time},content=#{content} where id=#{id}")
    void update(Diary diary);
    @Select("select *from diary where uid=#{uid}")
    List<Diary> selectByUid(Integer uid);
    @Select("select *from diary where id=#{id}")
    Diary selectById(Integer id);
}
