package com.space.web.servlet;

import com.space.pojo.User;
import com.space.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        UserServiceImpl userService=new UserServiceImpl();
        userService.insert(user);
        //获取新建用户的账号
        Integer account = userService.getMaxId();



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
