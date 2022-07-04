package com.space.mapper;

import com.space.pojo.Style;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StyleMapper {

    @Select("select * from style")
    List<Style> selectAll();

    @Select("select * from style where id=#{id}")
    Style selectById(Integer id);

    @Select("select * from style where uid=#{uid}")
    Style selectByUid(Integer uid);

    @Insert("insert into style ( uid) values(#{uid})")
    void insert(Style style);

    @Delete("delete from  style where id=#{id}")
    void deleteById(Integer id);

    @Update("update style set type=#{type} where uid=#{uid}")
    void updateType(@Param("uid") Integer uid, @Param("type") String type);

}
