package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.User;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends BaseServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String username = request.getParameter("username");
////        String password = request.getParameter("password");
//
//        String username = jsonObject.getString("name");
//        String password=jsonObject.getString("password");
//
//
//        User user=new User();
//        user.setPassword(password);
//        user.setUsername(username);
//        UserServiceImpl userService=new UserServiceImpl();
//        userService.insert(user);
//        //获取新建用户的账号
//        Integer account = userService.getMaxId();
//
//        response.getWriter().write(account);
//
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }

    public void register(){
        System.out.println("hello");
    }
}
