package com.space.service;

import com.space.pojo.Friends;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FriendsService {

    void insert(Friends friends);

    void delete(Friends friends);

    void update(Friends friends);

    List<Friends> selectById(Integer id);
}
