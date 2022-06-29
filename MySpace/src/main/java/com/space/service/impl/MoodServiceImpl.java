package com.space.service.impl;

import com.space.mapper.MoodMapper;
import com.space.pojo.Mood;
import com.space.service.MoodService;
import com.space.util.SqlSessionFactoryUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MoodServiceImpl implements MoodService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void insert(Mood mood) {
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        mapper.insert(mood);
        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        mapper.delete(id);
        session.commit();
        session.close();
    }

    @Override
    public void update(Mood mood) {
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        mapper.update(mood);
        session.commit();
        session.close();
    }

    @Override
    public List<Mood> selectByUid(Integer uid) {
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        List<Mood> moods = mapper.selectByUid(uid);
        session.commit();
        session.close();
        return moods;
    }

    @Override
    public Mood selectById(Integer id) {
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        Mood mood = mapper.selectById(id);
        session.commit();
        session.close();
        return mood;
    }
}
