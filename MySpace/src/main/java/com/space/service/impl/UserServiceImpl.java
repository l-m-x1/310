package com.space.service.impl;

import com.space.mapper.UserMapper;
import com.space.pojo.User;
import com.space.service.UserService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<User> selectAll() {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        //System.out.println(users);
        session.close();
        return users;
    }

    @Override
    public void insert(User user) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.insert(user);
        session.commit();
        session.close();
    }

    @Override
    public User selectById(Integer id) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectById(id);
        session.commit();
        session.close();
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }

    @Override
    public void updateUsername(Integer id,String username) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.updateUsername(id,username);
        session.commit();
        session.close();
    }

    @Override
    public void updatePassword(Integer id, String password) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.updateUsername(id,password);
        session.commit();
        session.close();
    }

    @Override
    public User select(Long account, String password) {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.select(account, password);
        session.commit();
        session.close();
        return user;
    }

}
