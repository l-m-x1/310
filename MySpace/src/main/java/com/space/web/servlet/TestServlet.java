package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.Diary;
import com.space.util.DiskFileItemFactoryUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

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

        DiskFileItemFactory fileItemFactory = DiskFileItemFactoryUtils.getDiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

        List<FileItem> fileItems;
        try {
            fileItems = fileUpload.parseRequest(req);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        for (FileItem item : fileItems) {
            if(item.isFormField()){

                System.out.println(item.getFieldName()+":"+item.getString());
            }
            else {
                InputStream inputStream = item.getInputStream();
                String itemName = item.getName();
                FileOutputStream outputStream = new FileOutputStream("./src/main/webapp/photos/"+itemName);
                IOUtils.copy(inputStream,outputStream);
                outputStream.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

