package com.space.web.servlet;

import com.space.pojo.User;
import com.space.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int account= Integer.parseInt(request.getParameter("id"));
        String password=request.getParameter("password");

        UserServiceImpl userService=new UserServiceImpl();
        User user = userService.select(account, password);
        if(user!=null)
        {

        }else {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
