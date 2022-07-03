package com.space.service.impl;

import com.space.mapper.TrendsMapper;
import com.space.mapper.UserMapper;
import com.space.pojo.Trends;
import com.space.service.TrendsService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TrendsServiceImpl implements TrendsService {

    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Trends> selectAll() {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        List<Trends> trends = mapper.selectAll();
        session.close();
        return trends;
    }

    @Override
    public Trends selectById(Integer id) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        Trends trends = mapper.selectById(id);
        session.close();
        return trends;
    }

    @Override
    public List<Trends> selectByUid(Integer uid) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        List<Trends> trends = mapper.selectByUid(uid);
        session.close();
        return trends;
    }

    @Override
    public void insert(Trends trends) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        mapper.insert(trends);
        session.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }

    @Override
    public void deleteByUid(Integer uid) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        mapper.deleteByUid(uid);
        session.commit();
        session.close();
    }

    @Override
    public void updateLikes(Integer id, Integer likes) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        mapper.updateLikes(id,likes);
        session.commit();
        session.close();
    }

    @Override
    public List<Trends> selectByUids(int[] uids) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        List<Trends> trends = mapper.selectByUids(uids);
        session.close();
        return trends;
    }

    @Override
    public void updateContent(Integer id, String content) {
        SqlSession session = factory.openSession();
        TrendsMapper mapper = session.getMapper(TrendsMapper.class);
        mapper.updateContent(id,content);
        session.commit();
        session.close();
    }
}
