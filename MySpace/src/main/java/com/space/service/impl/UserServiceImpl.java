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
        System.out.println(users);
        session.close();
        return null;
    }
}
