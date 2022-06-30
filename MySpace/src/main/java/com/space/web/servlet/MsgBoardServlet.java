package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.MsgBoard;
import com.space.service.impl.MsgBoardServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MsgBoardServlet")
public class MsgBoardServlet extends HttpServlet {
    private  HttpServletRequest request;
    private HttpServletResponse response;

    private MsgBoardServiceImpl msgBoardImpl=new MsgBoardServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request=request;
        this.response=response;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    public void insert(){
        int wrid = Integer.parseInt(request.getParameter("wrid"));
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        int uid = Integer.parseInt(request.getParameter("uid"));
        MsgBoard msgBoard=new MsgBoard();
        msgBoard.setContent(content);
        msgBoard.setWrid(wrid);
        msgBoard.setTime(time);
        msgBoard.setUid(uid);
        //楼层数并未处理

        msgBoardImpl.insert(msgBoard);

    }

    public void selectByUid() throws IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        List<MsgBoard> boards = msgBoardImpl.selectByUid(uid);


        String jsonString = JSON.toJSONString(boards);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(){
        int id = Integer.parseInt(request.getParameter("id"));
        msgBoardImpl.deleteById(id);
    }


}
