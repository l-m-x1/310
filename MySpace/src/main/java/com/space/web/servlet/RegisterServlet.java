package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.Info;
import com.space.pojo.User;
import com.space.service.UserService;
import com.space.service.impl.InfoServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/Register/*")
public class RegisterServlet extends BaseServlet {
    public void register() throws IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUsername(jsonObject.getString("name"));
        user.setPassword(jsonObject.getString("password"));
        userService.insert(user);
        Integer maxId = userService.getMaxId();
        resp.getWriter().write(maxId.toString());
        Info info = new Info();
        info.setId(maxId);
        InfoServiceImpl infoService = new InfoServiceImpl();
        infoService.insert(info);
    }
}
