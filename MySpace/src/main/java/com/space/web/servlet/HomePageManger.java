package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.*;
import com.space.service.*;
import com.space.service.impl.*;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HomePage/*")
public class HomePageManger extends BaseServlet {
//    public void getAllInfo() throws IOException {
//        Integer uid = jsonObject.getInteger("uid");
//
//        HomePageInfo homePageInfo = new HomePageInfo();
//
//
//
//        InfoService infoService = new InfoServiceImpl();
//        Info info = infoService.selectById(uid);
//
//        UserServiceImpl userService = new UserServiceImpl();
//        User user = userService.selectById(uid);
//
//        DiaryService diaryService = new DiaryServiceImpl();
//        List<Diary> diaries = diaryService.selectByUid(uid);
//
//        PhotosService photosService=new PhotosServiceImpl();
//        List<Photos> photos = photosService.selectByUid(uid);
//
//        TrendsService trendsService=new TrendsServiceImpl();
//        List<Trends> trendsList = trendsService.selectByUid(uid);
//
//
//
//        homePageInfo.setInfo(info);
//        homePageInfo.setDiaryList(diaries);
//        homePageInfo.setUser(user);
//        homePageInfo.setDiarySum(diaries.size());
//        homePageInfo.setPhotoSum(photos.size());
//        homePageInfo.setTrendsSum(trendsList.size());
//
//        String ret = JSON.toJSONString(homePageInfo);
//        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(ret);
//    }

    public void logout() {
        HttpSession session = req.getSession();
        session.removeAttribute("username");
        session.removeAttribute("password");
    }

    public void getFriendList() throws IOException {
        JSONObject ret =new JSONObject();
        List<UserInfo> userInfos=new ArrayList<>();



//        Integer uid = jsonObject.getInteger("uid");
        Integer uid= (Integer) req.getSession().getAttribute("uid");
        FriendsService friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(uid);
        for (Friends friend : friends) {

            UserInfo homePageInfo=new UserInfo();

            Integer fid = friend.getFid();

            InfoServiceImpl infoService = new InfoServiceImpl();
            Info info = infoService.selectById(fid);
            homePageInfo.setAvatar(info.getAvatar());

            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.selectById(uid);
            homePageInfo.setUsername(user.getUsername());
            userInfos.add(homePageInfo);
        }
        ret.put("friendList",userInfos);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret.toJSONString());
    }

    public void getUserInfo() throws IOException {
        JSONObject ret =new JSONObject();

//        Integer uid = jsonObject.getInteger("uid");
        Integer uid= (Integer) req.getSession().getAttribute("uid");
        UserService userService=new  UserServiceImpl();
        User user = userService.selectById(uid);
        ret.put("username",user.getUsername());


        InfoService infoService=new InfoServiceImpl();
        Info info = infoService.selectById(uid);
        ret.put("avatar",info.getAvatar());

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret.toJSONString());
    }

    public void addFriend(){
        Integer uid = (Integer) req.getSession().getAttribute("uid");
        Integer fid = jsonObject.getInteger("fid");
//        FriendsService friendsService = new FriendsServiceImpl();
//        Friends friends=new Friends();
//        friends.setId(uid);
//        friends.setFid(fid);
//        friends.setAccess(3);
//        friendsService.insert(friends);

        AddFriService addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setFrom(uid);
        addFriMsg.setTo(fid);
        addFriService.insert(addFriMsg);
    }

    public void accept(){
        Integer uid = (Integer) req.getSession().getAttribute("uid");
        Integer fid = jsonObject.getInteger("fid");
        AddFriServiceImpl addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setTo(uid);
        addFriMsg.setFrom(fid);
        addFriService.delete(addFriMsg);
        FriendsService friendsService = new FriendsServiceImpl();
        Friends friends=new Friends();
        friends.setId(uid);
        friends.setFid(fid);

        friends.setAccess(3);
        friendsService.insert(friends);
    }

    public void getAddFriMsg() throws IOException {
        Integer uid = (Integer) req.getSession().getAttribute("uid");
        AddFriService addFriService = new AddFriServiceImpl();
        List<AddFriMsg> addFriMsgs = addFriService.selectByTo(uid);
        ArrayList<Integer> froms = new ArrayList<>();
        for (AddFriMsg addFriMsg:addFriMsgs){
            froms.add(addFriMsg.getFrom());
        }
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(froms));
    }
}
