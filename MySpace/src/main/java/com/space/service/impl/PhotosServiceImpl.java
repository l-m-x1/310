package com.space.service.impl;

import com.space.mapper.PhotosMapper;
import com.space.mapper.UserMapper;
import com.space.pojo.Photos;
import com.space.service.PhotosService;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PhotosServiceImpl implements PhotosService {

    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Photos> selectAll() {
        SqlSession session = factory.openSession();
        PhotosMapper mapper = session.getMapper(PhotosMapper.class);
        List<Photos> photos = mapper.selectAll();
        session.close();
        return photos;
    }

    @Override
    public Photos selectById(Integer id) {
        SqlSession session = factory.openSession();
        PhotosMapper mapper = session.getMapper(PhotosMapper.class);
        Photos photo=mapper.selectById(id);
        session.close();
        return photo;
    }

    @Override
    public List<Photos> selectByUid(Integer uid) {
        SqlSession session = factory.openSession();
        PhotosMapper mapper = session.getMapper(PhotosMapper.class);
        List<Photos> photos = mapper.selectByUid(uid);
        session.close();
        return photos;
    }


    @Override
    public void insert(Photos photos) {
        SqlSession session = factory.openSession();
        PhotosMapper mapper = session.getMapper(PhotosMapper.class);
        mapper.insert(photos);
        session.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = factory.openSession();
        PhotosMapper mapper = session.getMapper(PhotosMapper.class);
        mapper.deleteById(id);
        session.commit();
        session.close();
    }
}
