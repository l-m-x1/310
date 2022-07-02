package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Photos;
import com.space.service.PhotosService;
import com.space.service.impl.PhotosServiceImpl;
import com.space.util.DiskFileItemFactoryUtils;
import com.space.web.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/Photos/*")
public class PhotosServlet extends HttpServlet {

    protected HttpServletRequest req;
    protected HttpServletResponse resp;
////    private  HttpServletRequest request;
////    private HttpServletResponse response;
////
////    private PhotosService photosImpl=new PhotosServiceImpl();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        this.request=request;
////        this.response=response;
//
////        String requestBody = IOUtils.toString(request.getInputStream());
////        FileOutputStream fileOutputStream = new FileOutputStream("./tp");
////        IOUtils.copy(request.getInputStream(),fileOutputStream);
////        fileOutputStream.close();
//
//        DiskFileItemFactory fileItemFactory = DiskFileItemFactoryUtils.getDiskFileItemFactory();
//        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
//
//        List<FileItem> fileItems;
//        try {
//            fileItems = fileUpload.parseRequest(request);
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
//
//        doGet(request,response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
////    public void showPhotos() throws IOException {
////        int uid = Integer.parseInt(request.getParameter("uid"));
////        List<Photos> photos = photosImpl.selectByUid(uid);
////        String jsonString = JSON.toJSONString(photos);
////        response.setContentType("text/json;charset=utf-8");
////        response.getWriter().write(jsonString);
////    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req=req;this.resp=resp;
        upload();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    public void upload() throws IOException {
        Integer id = (Integer) req.getSession().getAttribute("id");
//        Integer id=1;
        PhotosService photosService = new PhotosServiceImpl();


        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems;
        try {
            fileItems= fileUpload.parseRequest(req);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

        for(FileItem fileItem:fileItems){
            if(!fileItem.isFormField()){
                InputStream inputStream = fileItem.getInputStream();
                System.out.println(fileItem.getName());
                String fileName=fileItem.getName();
                int index = fileName.lastIndexOf('.');
                String  suffix=fileName.substring(index);
                String path="./src/main/webapp/photos/"+UUID.randomUUID()+suffix;
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                IOUtils.copy(inputStream,fileOutputStream);
                Photos photos = new Photos();
                photos.setUid(id);
                photos.setPath(path);
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s = format.format(date);
                photos.setTime(s);
                photosService.insert(photos);
            }
        }
    }

    public void getPhotos() throws IOException {
        Integer uid= (Integer) req.getSession().getAttribute("id");
        PhotosService photosService = new PhotosServiceImpl();
        List<Photos> photos = photosService.selectByUid(uid);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(photos));
    }


}
