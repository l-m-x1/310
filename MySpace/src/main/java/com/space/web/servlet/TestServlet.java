package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.io.*;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        JSONObject repsObject = new JSONObject();
//        String param="";
//        try {
//            BufferedReader streamReader = new BufferedReader( new InputStreamReader(req.getInputStream(), "UTF-8"));
//            StringBuilder responseStrBuilder = new StringBuilder();
//            String inputStr;
//            while ((inputStr = streamReader.readLine()) != null){
//                responseStrBuilder.append(inputStr);
//            }
//        }catch (Exception e){
//            repsObject.put("code",500);
//            repsObject.put("message","出现错误");
//        }

//        ServletInputStream inputStream = req.getInputStream();
//        while (inputStream.)

        ServletInputStream inputStream = req.getInputStream();

        String s = IOUtils.toString(inputStream);
        System.out.println(s);
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject.getString("data"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

