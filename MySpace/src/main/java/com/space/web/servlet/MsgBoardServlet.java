package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.MsgBoard;
import com.space.service.MsgBoardService;
import com.space.service.impl.MsgBoardServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/MsgBoard/*")
public class MsgBoardServlet extends BaseServlet {

    MsgBoardService msgBoardService=new MsgBoardServiceImpl();


    //添加一条留言板数据
    public void add(){

        HttpSession session=req.getSession();
        //session.setAttribute("id",30);
        MsgBoard msgBoard=new MsgBoard();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msgBoard.setUid((Integer) session.getAttribute("id"));
        msgBoard.setContent(jsonObject.getString("content"));
        msgBoard.setTime(simpleDateFormat.format(new Date()));
        msgBoard.setWrid(jsonObject.getInteger("wrid"));
        msgBoard.setFloor(jsonObject.getInteger("floor"));
        msgBoardService.insert(msgBoard);

    }

    //展示该用户的相关留言板
    public void show() throws IOException {
        HttpSession session=req.getSession();
        //session.setAttribute("id",30);
        Integer uid = (Integer) session.getAttribute("id");
        List<MsgBoard> boards = msgBoardService.selectByUid(uid);

        String jsonString = JSON.toJSONString(boards);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }


    //删除单条留言板
    public void deleteById(){
        int id = jsonObject.getInteger("id");
        msgBoardService.deleteById(id);
    }


}
