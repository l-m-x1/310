package com.space.service;

import com.space.pojo.AddFriMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AddFriService {


    void insert(AddFriMsg addFriMsg);

    void delete(AddFriMsg addFriMsg);

    List<AddFriMsg> select(Integer to);
}
