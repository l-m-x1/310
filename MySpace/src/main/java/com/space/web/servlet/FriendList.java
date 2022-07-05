package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.space.pojo.Friends;
import com.space.pojo.User;
import com.space.service.FriendsService;
import com.space.service.impl.FriendsServiceImpl;
import com.space.service.impl.UserServiceImpl;
import com.space.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Friend/*")
public class FriendList extends BaseServlet {
    public void getFriendList() throws IOException {
        JSONObject ret =new JSONObject();
        class ret{
            @JSONField(ordinal = 2)
            public String name;
            @JSONField(ordinal = 1)
            public String avatar;
            @JSONField(ordinal = 3)
            public Integer id;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
        List<ret> retList=new ArrayList<>();
        Integer uid= (Integer) req.getSession().getAttribute("id");
        FriendsService friendsService = new FriendsServiceImpl();
        List<Friends> friends = friendsService.selectById(uid);
        UserServiceImpl userService = new UserServiceImpl();
        for (Friends friend : friends) {
            ret tmp = new ret();
            Integer fid = friend.getFid();
            User user = userService.selectById(fid);
            tmp.name=user.getUsername();
            tmp.avatar=user.getAvatar();
            tmp.id=user.getId();
            retList.add(tmp);
        }
        resp.setContentType("text/plain;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(retList));
    }
}
