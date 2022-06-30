package com.space.mapper;

import com.space.pojo.Trends;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TrendsMapper {

    @Select("select * from trends")
    List<Trends>  selectAll();

    @Select("select * from trends where id=#{id}")
    Trends selectById(Integer id);

    @Select("select * from trends where uid=#{uid}")
    List<Trends> selectByUid(Integer uid);

    List<Trends> selectByUids(@Param("uids") int[] uids);

    @Insert("insert into trends(content,uid) values (#{content},#{uid})")
    void insert(Trends trends);

    @Delete("delete from trends where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from trends where uid=#{uid}")
    void deleteByUid(Integer uid);

    @Update("update trends set likes = #{likes} where id=#{id} ;")
    void updateLikes(@Param("id") Integer id, @Param("likes") Integer likes);
}
