package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Friends;
import com.space.pojo.Trends;
import com.space.service.TrendsService;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.TrendsServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( "/TrendsServlet")
public class TrendsServlet extends BaseServlet {

   TrendsService trendsService=new TrendsServiceImpl();
    HttpSession session=req.getSession();

    public void showTrends() throws IOException {
        //int[] uids = new int[0];

        List<Integer> uids=new ArrayList<>();
        Integer uid = (Integer) session.getAttribute("id");
        uids.add(uid);
        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(uid);
        for (Friends friend:friends) {
            uids.add(friend.getFid());
        }
        List<Trends> trends = trendsService.selectByUids(uids);

        String jsonString = JSON.toJSONString(trends);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void deleteById(){
        int id = jsonObject.getInteger("id");
        trendsService.deleteById(id);
    }
}
