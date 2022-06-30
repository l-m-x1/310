package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.*;
import com.space.service.*;
import com.space.service.impl.*;
import com.space.util.DiskFileItemFactoryUtils;
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
public class HomePageManger extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.req=req;
        this.resp=resp;
        String requestBody = IOUtils.toString(req.getInputStream());
        JSONObject jsonObject = JSON.parseObject(requestBody);

        String func=req.getParameter("func");
        try {
            this.getClass().getMethod(func).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req,resp);
//
//        DiskFileItemFactory fileItemFactory = DiskFileItemFactoryUtils.getDiskFileItemFactory();
//        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
//
//        List<FileItem> fileItems;
//        try {
//            fileItems = fileUpload.parseRequest(req);
//        } catch (FileUploadException e) {
//            throw new RuntimeException(e);
//        }
//        for (FileItem item : fileItems) {
//            if(item.isFormField()){
//
//                System.out.println(item.getFieldName()+":"+item.getString());
//            }
//            else {
//                InputStream inputStream = item.getInputStream();
//                String itemName = item.getName();
//                FileOutputStream outputStream = new FileOutputStream("./src/main/webapp/photos/"+itemName);
//                IOUtils.copy(inputStream,outputStream);
//                outputStream.close();
//            }
//        }

    }

    public void getAllInfo() throws IOException {
        Integer uid= Integer.valueOf(req.getParameter("uid"));
        String homepage_info = req.getParameter("homepage_info");
        JSONObject jsonObject = JSON.parseObject(homepage_info);

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
