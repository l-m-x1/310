package com.space.web.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test/*")
public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("hello");
        String s = IOUtils.toString(req.getInputStream());
        System.out.println(s);
//        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
//        List<FileItem> fileItems;
//        try {
//            fileItems= fileUpload.parseRequest(req);
//        } catch (FileUploadException e) {
//            throw new RuntimeException(e);
//        }
//        for(FileItem fileItem:fileItems){
//            if(!fileItem.isFormField()){
//                InputStream inputStream = fileItem.getInputStream();
//                System.out.println(fileItem.getName());
//                String fileName=fileItem.getName();
//                int index = fileName.lastIndexOf('.');
//                String  suffix=fileName.substring(index);
//                FileOutputStream fileOutputStream = new FileOutputStream("./src/main/webapp/photos/"+"3"+suffix);
//                IOUtils.copy(inputStream,fileOutputStream);
//            }
//            else {
//
//                String s = IOUtils.toString(fileItem.getInputStream());
//                System.out.println(s);
////                JSONObject jsonObject = JSON.parseObject();
////                System.out.println(jsonObject);
//            }
//
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
