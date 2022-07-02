package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Style;
import com.space.service.StyleService;
import com.space.service.impl.StyleServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( "/Style")
public class StyleServlet extends BaseServlet {

    StyleService styleService=new StyleServiceImpl();
    HttpSession session=req.getSession();
    public void show() throws IOException {
        Integer uid = (Integer) session.getAttribute("id");
        List<Style> styles = styleService.selectByUid(uid);
        String jsonString = JSON.toJSONString(styles);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void updateType(){
        Integer uid = (Integer) session.getAttribute("id");
        int type=jsonObject.getInteger("type");
        styleService.updateType(uid,type);
    }
}
