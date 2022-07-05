package com.space.web.servlet;

import com.space.pojo.User;
import com.space.service.UserService;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login/*")
public class LoginServlet extends BaseServlet {

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
