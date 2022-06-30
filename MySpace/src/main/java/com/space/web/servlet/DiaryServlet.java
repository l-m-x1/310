package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.Diary;
import com.space.service.DiaryService;
import com.space.service.impl.DiaryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/***
 * 向/Diary发送请求，参数func,diary
 * func为调用方法名
 * diary封装diary信息
 */
@WebServlet("/Diary")
public class DiaryServlet extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.req=req;
        this.resp=resp;
        String func=req.getParameter("func");
        try {
            this.getClass().getMethod(func).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.doGet(req, resp);
    }

    public void getDiaryInfo() throws IOException {
        Integer uid= Integer.valueOf(req.getParameter("uid"));

        DiaryService diaryService = new DiaryServiceImpl();
        List<Diary> diaries = diaryService.selectByUid(uid);
        String pageString = JSON.toJSONString(diaries);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(pageString);
    }
    public void deleteDiary(){
        Integer id= Integer.valueOf(req.getParameter("id"));

        DiaryService diaryService=new DiaryServiceImpl();
        diaryService.delete(id);
    }
    public void addDiary(){
        String diaryString = req.getParameter("diary");
        JSONObject jsonObject = JSON.parseObject(diaryString);
        Integer uid= Integer.valueOf(req.getParameter("uid"));
        Diary diary = new Diary();
        diary.setUid(uid);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        diary.setTime(s);
        diary.setContent(jsonObject.getString("content"));

        DiaryService diaryService=new DiaryServiceImpl();
        diaryService.insert(diary);
    }
    public void modifyDiary(){
        String diaryString = req.getParameter("diary");
        JSONObject jsonObject = JSON.parseObject(diaryString);
        Diary diary=new Diary();

//        diary.setContent();
    }
}
