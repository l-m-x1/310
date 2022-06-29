package com.space.service.impl;

import com.space.mapper.InfoMapper;
import com.space.pojo.Info;
import com.space.service.InfoService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class InfoServiceImpl implements InfoService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void insert(Info info) {
        SqlSession session = factory.openSession();
        InfoMapper mapper = session.getMapper(InfoMapper.class);
        mapper.insert(info);
        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = factory.openSession();
        InfoMapper mapper = session.getMapper(InfoMapper.class);
        mapper.delete(id);
        session.commit();
        session.close();
    }

    @Override
    public void update(Info info) {
        SqlSession session = factory.openSession();
        InfoMapper mapper = session.getMapper(InfoMapper.class);
        mapper.update(info);
        session.commit();
        session.close();
    }

    @Override
    public Info selectById(Integer id) {
        SqlSession session = factory.openSession();
        InfoMapper mapper = session.getMapper(InfoMapper.class);
        Info info = mapper.selectById(id);
        session.commit();
        session.close();
        return info;
    }
}
