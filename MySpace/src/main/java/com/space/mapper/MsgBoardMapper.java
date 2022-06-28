package com.space.mapper;

import com.space.pojo.MsgBoard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

    public interface MsgBoardMapper {
        @Select("select *from msg_board")
        List<MsgBoard> selectAll();


        @Select("select *from msg_board where uid=#{uid}")
        List<MsgBoard> selectByUid(Integer uid);


        void insert(MsgBoard msgBoard);


        @Delete("delete from msg_board where id=#{id} ;")
        void deleteById(Integer id);


    }


