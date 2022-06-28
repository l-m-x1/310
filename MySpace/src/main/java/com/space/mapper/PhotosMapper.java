package com.space.mapper;

import com.space.pojo.Photos;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhotosMapper {

    @Select("select * from photos")
    List<Photos> selectAll();

    @Select("select * from photos where id=#{id}")
    List<Photos> selectById(Integer id);

    //@Insert()
}
