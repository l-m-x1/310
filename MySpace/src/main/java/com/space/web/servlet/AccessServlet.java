package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.space.pojo.Friends;
import com.space.pojo.User;
import com.space.service.UserService;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Access/*")
public class AccessServlet extends BaseServlet {

    public void setAccess(){
        FriendsServiceImpl friendsService = new FriendsServiceImpl();

        Integer uid=(Integer) req.getSession().getAttribute("id");
        Integer fid=(Integer) jsonObject.getInteger("account");
        Friends friends = new Friends();
        friends.setId(uid);
        friends.setFid(fid);
        Friends friends1 = friendsService.selectAccess(friends);

        if(friends1.getAccess()==1){
            friends.setAccess(0);
        }
        else {
            friends.setAccess(1);
        }


        friendsService.update(friends);
    }

    public void getAllAccess() throws IOException {
        Integer id=(Integer) req.getSession().getAttribute("id");
        class ret{

            @JSONField(ordinal = 1)
            public String name;
            @JSONField(ordinal = 2)
            public Integer account;
            @JSONField(ordinal = 3)
            public Integer permission;


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getAccount() {
                return account;
            }

            public void setAccount(Integer account) {
                this.account = account;
            }

            public Integer getPermission() {
                return permission;
            }

            public void setPermission(Integer permission) {
                this.permission = permission;
            }
        }

        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(id);

        UserServiceImpl userService = new UserServiceImpl();
        List<ret> retList=new ArrayList<>();
        for(Friends friend:friends){
            ret ret = new ret();
            ret.account=friend.getFid();
            User user = userService.selectById(ret.account);
            ret.name=user.getUsername();
            ret.permission=friend.getAccess();
            retList.add(ret);
        }
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(retList));
    }

    public void getPermission() throws IOException {
        Integer id=(Integer) req.getSession().getAttribute("id");
        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        Friends friends = new Friends();
        friends.setFid(id);
        friends.setId(jsonObject.getInteger("id"));
        Friends friends1 = friendsService.selectAccess(friends);

        if(friends1.getAccess()==1){
            resp.getWriter().write("permission");
        }
        else {
            resp.getWriter().write("refused");
        }

    }

    public void getUserInfo() throws IOException {
        JSONObject ret =new JSONObject();
//        Integer uid= (Integer) req.getSession().getAttribute("id");
        Integer uid=jsonObject.getInteger("id");
        UserService userService=new  UserServiceImpl();
        User user = userService.selectById(uid);
        ret.put("userName",user.getUsername());
        ret.put("userAvatar",user.getAvatar());

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret.toJSONString());
    }
}
