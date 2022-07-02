package com.space.web.servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Info;
import com.space.service.InfoService;
import com.space.service.impl.InfoServiceImpl;
import com.space.web.BaseServlet;

import java.io.IOException;

public class InfoServlet extends BaseServlet {
    public void getInfo() throws IOException {
//        Integer id = jsonObject.getInteger("uid");
        Integer id = (Integer) req.getSession().getAttribute("id");
        InfoService infoService = new InfoServiceImpl();

        Info info = infoService.selectById(id);
        String ret = JSON.toJSONString(info);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(ret);
    }
    public void modifyInfo(){
        Integer id = (Integer) req.getSession().getAttribute("id");
        InfoServiceImpl infoService = new InfoServiceImpl();
        Info info = infoService.selectById(id);
        if(!jsonObject.getString("city").isEmpty()){
            info.setCity(jsonObject.getString("city"));
        }
        if(!jsonObject.getString("gender").isEmpty()){
            info.setGender(jsonObject.getString("gender"));
        }
        if(!jsonObject.getString("birthday").isEmpty()){
            info.setBirthday(jsonObject.getString("birthday"));
        }
        infoService.update(info);
    }
}
