package com.space.service.impl;

import com.space.pojo.User;
import org.junit.Test;

import java.util.List;

public class UserServiceImplTest {

    @Test
    public void testSelectAll() {
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.selectAll();
        System.out.println(users);
    }
    @Test
    public void testInsert(){
        String username="c23";
        String password="12345";
        int account= 12345678;

        User user=new User();
        user.setUsername(username);
        user.setAccount(account);
        user.setPassword(password);

        UserServiceImpl userService=new UserServiceImpl();
        userService.insert(user);

        List<User> users = userService.selectAll();
        System.out.println(users);


    }


    @Test
    public void testUpdateUsername(){

        int id=9;
        String username="tom";
        UserServiceImpl userService=new UserServiceImpl();
        userService.updateUsername(9,username);
        User user = userService.selectById(9);
        System.out.println(user);


    }

    @Test
    public void testDelete(){
        int id=4;
        UserServiceImpl userService=new UserServiceImpl();
        userService.deleteById(id);
        List<User> users = userService.selectAll();
        System.out.println(users);
    }




}