package com.space.service.impl;

import com.space.mapper.MsgBoardMapper;
import com.space.mapper.UserMapper;
import com.space.pojo.MsgBoard;
import com.space.pojo.User;
import com.space.service.MsgBoardService;
import org.apache.ibatis.session.SqlSession;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MsgBoardServiceImpl implements MsgBoardService {
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<MsgBoard> selectAll() {
        SqlSession session = factory.openSession();
        MsgBoardMapper mapper = session.getMapper(MsgBoardMapper.class);
        List<MsgBoard> boards = mapper.selectAll();
        //System.out.println(users);
        session.close();
        return boards;

    }

    @Override
    public void insert(MsgBoard msgBoard) {
        SqlSession session = factory.openSession();
        MsgBoardMapper mapper = session.getMapper(MsgBoardMapper.class);
        mapper.insert(msgBoard);
        session.commit();
        session.close();
    }

    @Override
    public List<MsgBoard> selectById(Integer id) {
        SqlSession session = factory.openSession();
        MsgBoardMapper mapper = session.getMapper(MsgBoardMapper.class);
        List<MsgBoard> boards = mapper.selectById(id);

        session.close();
        return boards;
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = factory.openSession();
        MsgBoardMapper mapper = session.getMapper(MsgBoardMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }
}
