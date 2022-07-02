package com.space.service.impl;

import com.space.mapper.StyleMapper;
import com.space.pojo.Style;
import com.space.service.StyleService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StyleServiceImpl implements StyleService {

    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession session = factory.openSession();
    StyleMapper mapper = session.getMapper(StyleMapper.class);
    @Override
    public List<Style> selectAll() {

        List<Style> styles = mapper.selectAll();
        session.close();
        return styles;
    }

    @Override
    public Style selectById(Integer id) {
//        SqlSession session = factory.openSession();
//        StyleMapper mapper = session.getMapper(StyleMapper.class);
        Style styles = mapper.selectById(id);
        session.close();
        return styles;
    }

    @Override
    public List<Style> selectByUid(Integer uid) {
//        SqlSession session = factory.openSession();
//        StyleMapper mapper = session.getMapper(StyleMapper.class);
        List<Style> styles = mapper.selectByUid(uid);
        session.close();
        return styles;
    }

    @Override
    public void insert(Style style) {
//        SqlSession session = factory.openSession();
//        StyleMapper mapper = session.getMapper(StyleMapper.class);
        mapper.insert(style);
        session.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
//        SqlSession session = factory.openSession();
//        StyleMapper mapper = session.getMapper(StyleMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();

    }

    @Override
    public void updateType(Integer uid, Integer type) {
//        SqlSession session = factory.openSession();
//        StyleMapper mapper = session.getMapper(StyleMapper.class);
        mapper.updateType(uid,type);
        session.commit();
        session.close();
    }
}
