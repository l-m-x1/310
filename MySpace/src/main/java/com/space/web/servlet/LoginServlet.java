package com.space.web.servlet;

import com.space.pojo.User;
import com.space.service.UserService;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/Login/*")
public class LoginServlet extends BaseServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        int account= Integer.parseInt(request.getParameter("id"));
//        String password=request.getParameter("password");
//
//        UserServiceImpl userService=new UserServiceImpl();
//        User user = userService.select(account, password);
//        if(user!=null)
//        {
//            System.out.println("success");
//        }else {
//            System.out.println("fail");
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
    public void login() throws IOException {
        UserService userService = new UserServiceImpl();
        Integer id = jsonObject.getInteger("id");
        User user = userService.selectById(id);
        String password = jsonObject.getString("password");
        if(user==null){
            resp.getWriter().write("account error");
        }
        else if(!user.getPassword().equals(password)){
            resp.getWriter().write("password error");
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("id",id);
            session.setAttribute("name",user.getUsername());
            session.setAttribute("password",password);
            resp.getWriter().write("successfully login");
        }
    }
}
