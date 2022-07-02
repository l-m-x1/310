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
import java.util.List;

@WebServlet("/MsgBoard")
public class MsgBoardServlet extends BaseServlet {

    MsgBoardService msgBoardService=new MsgBoardServiceImpl();
    HttpSession session=req.getSession();
    //添加一条留言板数据
    public void add(){
//        int wrid = Integer.parseInt(request.getParameter("wrid"));
//        String content = request.getParameter("content");
//        String time = request.getParameter("time");
//        int uid = Integer.parseInt(request.getParameter("uid"));
//        MsgBoard msgBoard=new MsgBoard();
//        msgBoard.setContent(content);
//        msgBoard.setWrid(wrid);
//        msgBoard.setTime(time);
//        msgBoard.setUid(uid);
//        //楼层数并未处理
//
//        msgBoardImpl.insert(msgBoard);


        MsgBoard msgBoard=new MsgBoard();
        msgBoard.setUid((Integer) session.getAttribute("id"));
        msgBoard.setContent(jsonObject.getString("content"));
        msgBoard.setTime(jsonObject.getString("time"));
        msgBoard.setWrid(jsonObject.getInteger("wrid"));
        msgBoard.setFloor(jsonObject.getInteger("floor"));
        msgBoardService.insert(msgBoard);

    }

    //展示该用户的相关留言板
    public void show() throws IOException {
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
