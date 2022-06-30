package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Photos;
import com.space.service.PhotosService;
import com.space.service.impl.PhotosServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/PhotosServlet")
public class PhotosServlet extends HttpServlet {
    private  HttpServletRequest request;
    private HttpServletResponse response;

    private PhotosService photosImpl=new PhotosServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request=request;
        this.response=response;
        doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void showPhotos() throws IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        List<Photos> photos = photosImpl.selectByUid(uid);
        String jsonString = JSON.toJSONString(photos);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


}
