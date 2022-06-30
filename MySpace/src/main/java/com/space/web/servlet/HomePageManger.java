package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.*;
import com.space.service.*;
import com.space.service.impl.*;
import com.space.util.DiskFileItemFactoryUtils;
import com.space.web.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@WebServlet("/HomePageManger")
public class HomePageManger extends BaseServlet {
    public void getAllInfo() throws IOException {
        Integer uid = jsonObject.getInteger("uid");

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

        String ret = JSON.toJSONString(homePageInfo);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret);
    }
}
