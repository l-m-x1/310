package com.space.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.util.DiskFileItemFactoryUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BaseServlet extends HttpServlet {
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    protected JSONObject jsonObject;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.req=req;
        this.resp=resp;
        String requestBody = IOUtils.toString(req.getInputStream());
        jsonObject = JSON.parseObject(requestBody);

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf('/');
        String func = uri.substring(index + 1);
        try {
            System.out.println(this.getClass());
            this.getClass().getMethod(func).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
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