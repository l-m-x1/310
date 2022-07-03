package com.space.service;

import com.space.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserService {
     List<User> selectAll();

     void insert(User user);

    User selectById(Integer id);


     void deleteById(Integer id);


     void updateUsername(Integer id,String username);


     void updatePassword(Integer id, String password);

     User select(Integer id, String password);

    Integer getMaxId();
    void updateAvatar(Integer id,String avatar);
}
