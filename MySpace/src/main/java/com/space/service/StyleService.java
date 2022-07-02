package com.space.service;

import com.space.pojo.Style;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StyleService {

    List<Style> selectAll();

    Style selectById(Integer id);

    List<Style> selectByUid(Integer uid);


    void insert(Style style);


    void deleteById(Integer id);


    void updateType( Integer uid, Integer type);

}
