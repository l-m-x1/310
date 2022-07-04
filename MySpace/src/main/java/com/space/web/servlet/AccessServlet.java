package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.space.pojo.Friends;
import com.space.pojo.User;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccessServlet extends BaseServlet {

    public void setAccess(){
        Integer uid=(Integer) req.getSession().getAttribute("id");
        Integer fid=(Integer) jsonObject.getInteger("account");
        Integer access=(Integer) jsonObject.getInteger("permission");
        Friends friends = new Friends();
        friends.setId(uid);
        friends.setFid(fid);
        friends.setAccess(access);
        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        friendsService.update(friends);
    }

    public void getAccess() throws IOException {
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
        }
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(retList));
    }

    public void getPermission() throws IOException {
        Integer id=(Integer) req.getSession().getAttribute("id");
        FriendsServiceImpl friendsService = new FriendsServiceImpl();
        Friends friends = new Friends();
        friends.setId(id);
        friends.setFid(jsonObject.getInteger("id"));
        Friends friends1 = friendsService.selectAccess(friends);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(friends1.getAccess());
    }
}
