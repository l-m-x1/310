package com.space.mapper;

import com.space.pojo.Diary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DiaryMapper {
    @Insert("insert into diary values (#{id},#{time},#{content})")
    public boolean insert(Diary diary);
    @Delete("delete from diary where id=#{id}")
    public boolean delete(Integer id);
    @Update("update diary set time=#{time},content=#{content}")
    public boolean update(Diary diary);
    @Select("select *from diary")
    public List<Diary> selectAll();
    @Select("select *from diary where id=#{id}")
    public Diary selectById(Integer id);
}
