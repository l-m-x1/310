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
        String contentType = req.getContentType();
        System.out.println(contentType+"13");
        if(contentType!=null&&contentType.contains("application/json")){
            String requestBody = IOUtils.toString(req.getInputStream());
            jsonObject = JSON.parseObject(requestBody);
        }

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf('/');
        String func = uri.substring(index + 1);
        try {
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