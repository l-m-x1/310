package com.space.mapper;

import com.space.pojo.AddFriMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddFriMapper {
    @Insert("insert into add_fri_msg values (#{from},#{to})")
    void insert(AddFriMsg addFriMsg);
    @Delete("delete from add_fri_msg where from=#{from} and to=#{to}")
    void delete(AddFriMsg addFriMsg);
    @Select("select *from add_fri_msg where to=#{to}")
    List<AddFriMsg> selectByTo(Integer to);

    @Select("select *from add_fri_msg where from=#{from}and to=#{to}")
    AddFriMsg selectSingle(AddFriMsg addFriMsg);
}
