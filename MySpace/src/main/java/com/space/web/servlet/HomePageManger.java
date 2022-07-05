package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.space.pojo.AddFriMsg;
import com.space.pojo.Friends;
import com.space.pojo.Style;
import com.space.pojo.User;
import com.space.service.AddFriService;
import com.space.service.FriendsService;
import com.space.service.UserService;
import com.space.service.impl.AddFriServiceImpl;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.StyleServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HomePage/*")
public class HomePageManger extends BaseServlet {


    public void logout() {
        HttpSession session = req.getSession();
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("password");
    }



    public void getUserInfo() throws IOException {
        JSONObject ret =new JSONObject();
        Integer uid= (Integer) req.getSession().getAttribute("id");
        UserService userService=new  UserServiceImpl();
        User user = userService.selectById(uid);
        ret.put("userName",user.getUsername());
        ret.put("userAvatar",user.getAvatar());

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret.toJSONString());
    }

    public void addFriend(){
        Integer uid = (Integer) req.getSession().getAttribute("id");
        Integer fid = jsonObject.getInteger("id");


        AddFriService addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setMsg_from(uid);
        addFriMsg.setMsg_to(fid);
        addFriService.insert(addFriMsg);
    }

    public void accept(){
        Integer uid = (Integer) req.getSession().getAttribute("id");
        Integer fid = jsonObject.getInteger("id");
        AddFriServiceImpl addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setMsg_to(uid);
        addFriMsg.setMsg_from(fid);
        addFriService.delete(addFriMsg);
        FriendsService friendsService = new FriendsServiceImpl();
        Friends friends=new Friends();
        friends.setId(uid);
        friends.setFid(fid);
        friends.setAccess(1);
        friendsService.insert(friends);
        friends.setId(fid);
        friends.setFid(uid);
        friends.setAccess(1);
        friendsService.insert(friends);
    }

    public void getAddFriMsg() throws IOException {

        class ret{
            @JSONField(ordinal = 1)
            public String avatar;
            @JSONField(ordinal = 2)
            public String name;
            @JSONField(ordinal = 3)
            public Integer id;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }
        Integer uid = (Integer) req.getSession().getAttribute("id");
        AddFriService addFriService = new AddFriServiceImpl();
        List<AddFriMsg> addFriMsgs = addFriService.selectByTo(uid);
        ArrayList<ret> froms = new ArrayList<>();
        UserServiceImpl userService = new UserServiceImpl();
        for (AddFriMsg addFriMsg:addFriMsgs){
            ret ret = new ret();
            User user = userService.selectById(addFriMsg.getMsg_from());
            ret.avatar =user.getAvatar();
            ret.name=user.getUsername();
            ret.id=user.getId();
            froms.add(ret);
        }
        resp.setContentType("text/json;charset=utf-8");
        String s = JSON.toJSONString(froms);
        resp.getWriter().write(s);
    }

    public void selectFriend() throws IOException {
        class ret{
            public String avatar;
            public Integer id;
            public String name;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

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
        }
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.selectById(jsonObject.getInteger("id"));
        ret ret = new ret();
        ret.avatar=user.getAvatar();
        ret.name=user.getUsername();
        ret.id=user.getId();
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(ret, SerializerFeature.WriteNullStringAsEmpty));
    }


    public void refuse(){
        Integer uid = (Integer) req.getSession().getAttribute("id");
        Integer fid = jsonObject.getInteger("id");
        AddFriServiceImpl addFriService = new AddFriServiceImpl();
        AddFriMsg addFriMsg = new AddFriMsg();
        addFriMsg.setMsg_to(uid);
        addFriMsg.setMsg_from(fid);
        addFriService.delete(addFriMsg);
    }


    public void getFriendDecoration() throws IOException {
        Integer fid = jsonObject.getInteger("id");
        System.out.println(fid);
        getDecoration(fid);

    }
    public void getDecoration(int uid) throws IOException {

        // Integer uid=213141521;
        Style style = new StyleServiceImpl().selectByUid(uid);

        if(style==null){
            resp.getWriter().write("no decoration");
        }
        String jsonString = JSON.toJSONString(style.getType());
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void changeMyDecoration(){
        HttpSession session=req.getSession();
        Integer uid = (Integer) session.getAttribute("id");
        changeDecoration(uid);
    }

    public void changeFriendDecoration() throws IOException {
        Integer fid = jsonObject.getInteger("id");
        changeDecoration(fid);

    }

    public void changeDecoration(int uid){

        //Integer uid=213141521;
        String type=jsonObject.getString("color");
        new StyleServiceImpl().updateType(uid,type);
    }

    public void getMyDecoration() throws IOException {
        HttpSession session=req.getSession();
        Integer uid = (Integer) session.getAttribute("id");
        getDecoration(uid);
    }


    public void deleteFriend(){
        Integer id=(Integer) req.getSession().getAttribute("id");
        Integer id1 = jsonObject.getInteger("id");
        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        Friends friends = new Friends();
        friends.setId(id);
        friends.setFid(id1);
        friendsService.delete(friends);
        friends.setFid(id);
        friends.setId(id1);
        friendsService.delete(friends);
    }
}
