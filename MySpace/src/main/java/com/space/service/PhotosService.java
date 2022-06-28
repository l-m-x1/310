package com.space.service;

import com.space.pojo.Photos;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhotosService {


    List<Photos> selectAll();


    Photos selectById(Integer id);

    List<Photos> selectByUid(Integer uid);


    void insert(Photos photos);


    void deleteById(Integer id);

}
