package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Info;
import com.space.pojo.Photos;
import com.space.pojo.User;
import com.space.service.InfoService;
import com.space.service.impl.InfoServiceImpl;
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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/Info/*")

public class InfoServlet extends BaseServlet {
    public void getInfo() throws IOException {
//        Integer id = jsonObject.getInteger("uid");
        Integer id = (Integer) req.getSession().getAttribute("id");
        InfoService infoService = new InfoServiceImpl();

        Info info = infoService.selectById(id);
        String ret = JSON.toJSONString(info);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret);
    }
    public void modifyInfo(){
        Integer id = (Integer) req.getSession().getAttribute("id");
        InfoServiceImpl infoService = new InfoServiceImpl();
        Info info = infoService.selectById(id);
        if(!jsonObject.getString("city").isEmpty()){
            info.setCity(jsonObject.getString("city"));
        }
        if(!jsonObject.getString("gender").isEmpty()){
            info.setGender(jsonObject.getString("gender"));
        }
        if(!jsonObject.getString("birthday").isEmpty()){
            info.setBirthday(jsonObject.getString("birthday"));
        }
        infoService.update(info);
    }


    public void modifyInfo1() throws IOException {
        Integer id=(Integer)req.getSession().getAttribute("id");
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems;
        Map<String,String>map=new HashMap<>();
        try {
            fileItems= fileUpload.parseRequest(req);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        for(FileItem fileItem:fileItems) {
            if (!fileItem.isFormField()) {
                InputStream inputStream = fileItem.getInputStream();
                System.out.println(fileItem.getName());
                String fileName=fileItem.getName();
                int index = fileName.lastIndexOf('.');
                String  suffix=fileName.substring(index);
                String newName= UUID.randomUUID()+suffix;
                String path="./src/main/webapp/photos/"+newName;
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                IOUtils.copy(inputStream,fileOutputStream);

                UserServiceImpl userService = new UserServiceImpl();
                userService.updateAvatar(id,"/photos/"+newName);
            }
            else {
                String fieldName=new String(fileItem.getFieldName().getBytes("GBK"), StandardCharsets.UTF_8);
                String value=fileItem.getString("UTF-8");
                map.put(fieldName,value);
            }
        }
        UserServiceImpl userService = new UserServiceImpl();
        userService.updateUsername(id,map.get("name"));
        InfoServiceImpl infoService = new InfoServiceImpl();
        Info info = new Info();
        info.setId(id);
        info.setBirthday(map.get("date"));
        info.setCity(map.get("hometown"));
        info.setGender(map.get("region"));
        info.setCompanyAddress(map.get("companyAddress"));
        info.setCompanyName(map.get("companyName"));
        info.setWork(map.get("work"));
        info.setAddress(map.get("address"));
        infoService.update(info);

    }

}
