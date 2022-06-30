package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Trends;
import com.space.service.TrendsService;
import com.space.service.impl.TrendsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( "/TrendsServlet")
public class TrendsServlet extends HttpServlet {

    private  HttpServletRequest request;
    private HttpServletResponse response;

    private TrendsService trendsImpl=new TrendsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request=request;
        this.response=response;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void showTrends() throws IOException {
        int[] uids = new int[0];
        List<Trends> trends = trendsImpl.selectByUids(uids);

        String jsonString = JSON.toJSONString(trends);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(){
        int id = Integer.parseInt(request.getParameter("id"));
        trendsImpl.deleteById(id);
    }
}
