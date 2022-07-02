package com.space.service.impl;

import com.space.mapper.AddFriMapper;
import com.space.pojo.AddFriMsg;
import com.space.service.AddFriService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AddFriServiceImpl implements AddFriService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void insert(AddFriMsg addFriMsg) {
        SqlSession session = factory.openSession();
        AddFriMapper mapper = session.getMapper(AddFriMapper.class);
        mapper.insert(addFriMsg);
        session.commit();
        session.close();
    }

    @Override
    public void delete(AddFriMsg addFriMsg) {
        SqlSession session = factory.openSession();
        AddFriMapper mapper = session.getMapper(AddFriMapper.class);
        mapper.delete(addFriMsg);
        session.commit();
        session.close();

    }

    @Override
    public List<AddFriMsg> selectByTo(Integer to) {
        SqlSession session = factory.openSession();
        AddFriMapper mapper = session.getMapper(AddFriMapper.class);
        return mapper.selectByTo(to);
    }

    @Override
    public AddFriMsg selectSingle(AddFriMsg addFriMsg) {
        SqlSession session = factory.openSession();
        AddFriMapper mapper = session.getMapper(AddFriMapper.class);
        return mapper.selectSingle(addFriMsg);
    }

}
