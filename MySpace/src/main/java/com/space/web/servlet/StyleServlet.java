package com.space.web.servlet;

import com.space.pojo.Style;
import com.space.service.StyleService;
import com.space.service.impl.StyleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StyleServlet", value = "/StyleServlet")
public class StyleServlet extends HttpServlet {
    private  HttpServletRequest request;
    private HttpServletResponse response;

    private StyleService styleImpel=new StyleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request=request;
        this.response=response;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void selectByUid(){
        int uid = Integer.parseInt(request.getParameter("uid"));
        List<Style> styles = styleImpel.selectByUid(uid);
    }

    public void updateType(){
        int id= Integer.parseInt(request.getParameter("id"));
        int type= Integer.parseInt(request.getParameter("type"));
        styleImpel.updateType(id,type);
    }
}
