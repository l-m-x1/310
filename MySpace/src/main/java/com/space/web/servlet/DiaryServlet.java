package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Diary;
import com.space.service.DiaryService;
import com.space.service.impl.DiaryServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public void modify(){

//        Integer uid = jsonObject.getInteger("uid");
        Integer uid = (Integer) req.getSession().getAttribute("id");
        Integer id = jsonObject.getInteger("id");

        Diary diary=new Diary();
        diary.setId(id);
        diary.setUid(uid);
        if(jsonObject.getString("text").isEmpty()){
            diary.setContent(jsonObject.getString("text"));
        }
        if(jsonObject.getString("name").isEmpty()){
            diary.setTitle(jsonObject.getString("name"));
        }
        DiaryService diaryService = new DiaryServiceImpl();
        diaryService.update(diary);
    }

    public void setLog(){
//        Integer uid = jsonObject.getInteger("uid");
        Integer uid=(Integer)req.getSession().getAttribute("id");
        Diary diary = new Diary();
        diary.setUid(uid);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        diary.setTime(s);
        diary.setTitle(jsonObject.getString("name"));
        diary.setContent(jsonObject.getString("content"));
        DiaryService diaryService=new DiaryServiceImpl();
        diaryService.insert(diary);
    }

    public void viewLogs() throws IOException {
//        Integer uid = jsonObject.getInteger("uid");

        class ret{
            public Integer id;
            public String name;
            public String text;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
        Integer uid= (Integer) req.getSession().getAttribute("uid");
        DiaryService diaryService = new DiaryServiceImpl();
        List<Diary> diaries = diaryService.selectByUid(uid);
        ArrayList<ret> rets = new ArrayList<>();
        for(Diary diary:diaries){
            ret ret = new ret();
            ret.id=diary.getId();
            ret.name=diary.getTitle();
            ret.text=diary.getContent();
            rets.add(ret);
        }
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(rets));
    }
}
