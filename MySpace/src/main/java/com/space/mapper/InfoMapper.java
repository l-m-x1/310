package com.space.mapper;

import com.space.pojo.Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InfoMapper {
    @Insert("insert into info(id) values (#{id})")
    void insert(Info info);

    @Delete("delete from info where id=#{id}")
    void delete(Integer id);

    @Update("update info set gender=#{gender},city=#{city},birthday=#{birthday}," +
            "work=#{work},companyName=#{companyName}," +
            "companyAddress=#{companyAddress},address=#{address} where id=#{id}")
    void update(Info info);

    @Select("select *from info where id=#{id}")
    Info selectById(Integer id);
}
