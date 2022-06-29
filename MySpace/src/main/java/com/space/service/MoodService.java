package com.space.service;

import com.space.pojo.Mood;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MoodService {

    void insert(Mood mood);

    void delete(Integer id);

    void update(Mood mood);

    List<Mood> selectByUid(Integer uid);

    Mood selectById(Integer id);
}
