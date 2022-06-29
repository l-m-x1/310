package com.space.service;

import com.space.pojo.Diary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DiaryService {

    void insert(Diary diary);

    void delete(Integer id);

    void update(Diary diary);

    List<Diary> selectByUid(Integer uid);

    Diary selectById(Integer id);
}
