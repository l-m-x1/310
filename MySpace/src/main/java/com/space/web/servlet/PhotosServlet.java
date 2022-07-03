package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.space.pojo.Photos;
import com.space.pojo.User;
import com.space.service.PhotosService;
import com.space.service.impl.PhotosServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.WebServlet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/Photos/*")
public class PhotosServlet extends BaseServlet {

//    protected HttpServletRequest req;
//    protected HttpServletResponse resp;
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


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        this.req=req;this.resp=resp;
////        upload();
//        getPhotos();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req,resp);
//    }

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
                String newName=UUID.randomUUID()+suffix;
                String path="./src/main/webapp/photos/"+newName;
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                IOUtils.copy(inputStream,fileOutputStream);
                Photos photos = new Photos();
                photos.setUid(id);
                photos.setPath("./photos/"+newName);
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String s = format.format(date);
                photos.setTime(s);
                photosService.insert(photos);
            }
        }
    }

    public void getPhotos() throws IOException {
        System.out.println("getPhotos");
        Integer uid= (Integer) req.getSession().getAttribute("id");
//        Integer uid=1;
        PhotosService photosService = new PhotosServiceImpl();
        List<Photos> photos = photosService.selectByUid(uid);

        class ret{
            public String date;
            public String url;
            public Integer id;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }
        List<ret> retMsg=new ArrayList<>();
        for (Photos photo:photos){
            ret ret = new ret();
            ret.date=photo.getTime();
            ret.url=photo.getPath();
            ret.id=photo.getId();
            retMsg.add(ret);
        }
        resp.setContentType("text/json;charset=utf-8");
        System.out.println(JSON.toJSONString(photos));
        System.out.println(JSON.toJSONString(retMsg));
        resp.getWriter().write(JSON.toJSONString(retMsg));
    }

    public void getAvatar() throws IOException {
        Integer uid=1;
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.selectById(uid);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(user.getAvatar());
    }

    public void deletePhotos(){
        Integer id = (Integer) req.getSession().getAttribute("id");
//        id=1;
        JSONArray deletes = jsonObject.getJSONArray("checkList");
        PhotosServiceImpl photosService = new PhotosServiceImpl();

        for(Object delete:deletes){

            photosService.deleteById(Integer.parseInt(delete.toString()));
        }
    }

    public void setAvatar(){

    }

}

