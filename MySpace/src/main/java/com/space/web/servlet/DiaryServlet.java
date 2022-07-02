package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Diary;
import com.space.service.DiaryService;
import com.space.service.impl.DiaryServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/***
 * 向/Diary发送请求，参数func,diary
 * func为调用方法名
 * diary封装diary信息
 */
@WebServlet("/Diary/*")
public class DiaryServlet extends BaseServlet {
    public void getDiaryInfo() throws IOException {
        Integer uid = jsonObject.getInteger("uid");
        DiaryService diaryService = new DiaryServiceImpl();
        List<Diary> diaries = diaryService.selectByUid(uid);
        String ret = JSON.toJSONString(diaries);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret);
    }
    public void deleteDiary(){
        Integer id = jsonObject.getInteger("id");
        DiaryService diaryService=new DiaryServiceImpl();
        diaryService.delete(id);
    }
    public void addDiary(){
        Integer uid = jsonObject.getInteger("uid");

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

        Integer uid = jsonObject.getInteger("uid");
        Integer id = jsonObject.getInteger("id");

        Diary diary=new Diary();
        diary.setId(id);
        diary.setUid(uid);
        diary.setContent(jsonObject.getString("content"));
        DiaryService diaryService = new DiaryServiceImpl();
        diaryService.update(diary);
    }
}
