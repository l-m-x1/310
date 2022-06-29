package com.space.service.impl;

import com.space.mapper.DiaryMapper;
import com.space.pojo.Diary;
import com.space.service.DiaryService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DiaryServiceImpl implements DiaryService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void insert(Diary diary) {
        SqlSession session = factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        mapper.insert(diary);
        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        mapper.delete(id);
        session.commit();
        session.close();

    }

    @Override
    public void update(Diary diary) {
        SqlSession session = factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        mapper.update(diary);
        session.commit();
        session.close();
    }

    @Override
    public List<Diary> selectByUid(Integer uid) {
        SqlSession session = factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        List<Diary> diaries = mapper.selectByUid(uid);
        session.commit();
        session.close();
        return diaries;
    }

    @Override
    public Diary selectById(Integer id) {
        SqlSession session = factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        Diary diary = mapper.selectById(id);
        session.commit();
        session.close();
        return diary;
    }
}
