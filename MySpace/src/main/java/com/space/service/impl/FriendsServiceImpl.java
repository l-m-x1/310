package com.space.service.impl;

import com.space.mapper.DiaryMapper;
import com.space.mapper.FriendsMapper;
import com.space.pojo.Diary;
import com.space.pojo.Friends;
import com.space.service.FriendsService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class FriendsServiceImpl implements FriendsService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void insert(Friends friends) {
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        mapper.insert(friends);
        session.commit();
        session.close();
    }

    @Override
    public void delete(Friends friends) {
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        mapper.delete(friends);
        session.commit();
        session.close();
    }

    @Override
    public void update(Friends friends) {
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        mapper.update(friends);
        session.commit();
        session.close();
    }

    @Override
    public List<Friends> selectById(Integer id) {
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        List<Friends> friends = mapper.selectById(id);
        session.commit();
        session.close();
        return friends;
    }

    @Override
    public Friends selectAccess(Friends friends) {
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        Friends friends1 = mapper.selectAccess(friends);
        return friends1;

    }
}
