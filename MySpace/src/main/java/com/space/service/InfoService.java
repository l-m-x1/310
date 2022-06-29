package com.space.service;

import com.space.pojo.Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InfoService {

    void insert(Info info);

    void delete(Integer id);

    void update(Info info);

    Info selectById(Integer id);
}
