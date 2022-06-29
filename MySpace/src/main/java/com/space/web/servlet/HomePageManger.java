package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.*;
import com.space.service.*;
import com.space.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
@WebServlet("/HomePage")
public class HomePageManger extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req=req;
        this.resp=resp;
        String func = req.getParameter("func");
        try {
            Method getAllInfo = this.getClass().getMethod("getAllInfo", Integer.class);
            getAllInfo.invoke(this,1);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
    }

    public void getAllInfo(Integer uid) throws IOException {
        HomePageInfo homePageInfo = new HomePageInfo();
        
        InfoService infoService = new InfoServiceImpl();
        Info info = infoService.selectById(uid);
        
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.selectById(uid);
        
        DiaryService diaryService = new DiaryServiceImpl();
        List<Diary> diaries = diaryService.selectByUid(uid);
        
        PhotosService photosService=new PhotosServiceImpl();
        List<Photos> photos = photosService.selectByUid(uid);
        
        TrendsService trendsService=new TrendsServiceImpl();
        List<Trends> trendsList = trendsService.selectByUid(uid);
        
        homePageInfo.setInfo(info);
        homePageInfo.setDiaryList(diaries);
        homePageInfo.setUser(user);
        homePageInfo.setDiarySum(diaries.size());
        homePageInfo.setPhotoSum(photos.size());
        homePageInfo.setTrendsSum(trendsList.size());
        String pageString = JSON.toJSONString(homePageInfo);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(pageString);
    }
}
