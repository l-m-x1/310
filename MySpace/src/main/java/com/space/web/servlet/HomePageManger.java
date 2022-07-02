package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.AddFriMsg;
import com.space.pojo.Friends;
import com.space.pojo.User;
import com.space.service.AddFriService;
import com.space.service.FriendsService;
import com.space.service.UserService;
import com.space.service.impl.AddFriServiceImpl;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.UserServiceImpl;
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
//        List<UserInfo> userInfos=new ArrayList<>();

        class ret{
            public String name;
            public String avatar;
        }
        List<ret> retList=new ArrayList<>();


//        Integer uid = jsonObject.getInteger("uid");
        Integer uid= (Integer) req.getSession().getAttribute("id");
        FriendsService friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(uid);
        for (Friends friend : friends) {

            ret tmp = new ret();
            Integer fid = friend.getFid();


            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.selectById(uid);
            tmp.name=user.getUsername();
            tmp.avatar=user.getAvatar();
            retList.add(tmp);
        }
//        ret.put("friendList",retList);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(retList));
    }

    public void getUserInfo() throws IOException {
        JSONObject ret =new JSONObject();

//        Integer uid = jsonObject.getInteger("uid");
        Integer uid= (Integer) req.getSession().getAttribute("id");
        UserService userService=new  UserServiceImpl();
        User user = userService.selectById(uid);
        ret.put("username",user.getUsername());
        ret.put("avatar",user.getAvatar());

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret.toJSONString());
    }

    public void addFriend(){
        Integer uid = (Integer) req.getSession().getAttribute("id");
        Integer fid = jsonObject.getInteger("fid");


        AddFriService addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setFrom(uid);
        addFriMsg.setTo(fid);
        addFriService.insert(addFriMsg);
    }

    public void accept(){
        Integer uid = (Integer) req.getSession().getAttribute("id");
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
        Integer uid = (Integer) req.getSession().getAttribute("id");
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
