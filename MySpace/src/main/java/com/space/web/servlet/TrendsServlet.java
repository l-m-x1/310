package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Friends;
import com.space.pojo.Trends;
import com.space.pojo.User;
import com.space.service.TrendsService;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.TrendsServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet( "/Trends/*")
public class TrendsServlet extends BaseServlet {
    class Ret{
        Integer id;
        String name;
        String text;
        String photo;
        String time;
        Integer cheer;
        String modify;

        public String getModify() {
            return modify;
        }

        public String getTime() {
            return time;
        }



        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getText() {
            return text;
        }

        public String getPhoto() {
            return photo;
        }

        public Integer getCheer() {
            return cheer;
        }
    }
   TrendsService trendsService=new TrendsServiceImpl();

    public void add() {

        HttpSession session=req.getSession();
        //session.setAttribute("id",2);
        Integer uid=(Integer) session.getAttribute("id");
        Trends trends=new Trends();
        trends.setContent(jsonObject.getString("text"));
        trends.setUid(uid);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        trends.setTime(simpleDateFormat.format(new Date()));

        trendsService.insert(trends);

    }

    public void showMyself() throws IOException {
        HttpSession session=req.getSession();
      //  session.setAttribute("id",2);
        Integer uid = (Integer) session.getAttribute("id");
        showAUser(uid);
    }

    public void showFriend() throws IOException {
        Integer fid =  jsonObject.getInteger("id");
        showAUser(fid);
    }

    public void showAUser(int uid) throws IOException {


       //获取好友
       //Integer uid=jsonObject.getInteger("id");



        List<Trends> trends = trendsService.selectByUid(uid);
        List<Ret> rets=new ArrayList<>();
        for (Trends trend:trends) {
            Ret ret=new Ret();
            ret.id=trend.getId();
            ret.time=trend.getTime();
            ret.text=trend.getContent();
            ret.cheer=trend.getLikes();
            User user = new UserServiceImpl().selectById(trend.getUid());
            ret.name=user.getUsername();
            ret.modify="false";
            ret.photo=user.getAvatar();
            rets.add(ret);
        }
        String jsonString = JSON.toJSONString(rets);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void show() throws IOException {
        //int[] uids = new int[0];
        HttpSession session=req.getSession();
      //  session.setAttribute("id",2);
        Integer uid = (Integer) session.getAttribute("id");
        List<Integer> uids1=new ArrayList<>();
        uids1.add(uid);

        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(uid);
        for (Friends friend:friends) {
            uids1.add(friend.getFid());
        }
        int[] uids=new int[uids1.size()];
        for (int i = 0; i < uids1.size(); i++) {
            uids[i]=uids1.get(i);
        }
        List<Trends> trends = trendsService.selectByUids(uids);

        List<Ret> rets=new ArrayList<>();
        for (Trends trend:trends) {
            Ret ret=new Ret();
            ret.id=trend.getId();
            ret.time=trend.getTime();
            ret.text=trend.getContent();
            ret.cheer=trend.getLikes();
            User user = new UserServiceImpl().selectById(trend.getUid());
            ret.name=user.getUsername();
            ret.photo=user.getAvatar();
            ret.modify="false";
            rets.add(ret);
        }

        String jsonString = JSON.toJSONString(rets);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void deleteById(){
        int id = Integer.parseInt(jsonObject.getString("id"));
        trendsService.deleteById(id);
    }


    public void updateLikes(){
        int id=Integer.parseInt(jsonObject.getString("id"));
        int likes=jsonObject.getInteger("cheer");
        trendsService.updateLikes(id,likes);
    }

    public void updateContent(){
        int id=Integer.parseInt(jsonObject.getString("id"));
        String content=jsonObject.getString("text");
        trendsService.updateContent(id,content);
    }
}
