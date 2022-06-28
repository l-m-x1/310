package com.space.service.impl;

import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void selectAll() {
        UserServiceImpl service = new UserServiceImpl();
        service.selectAll();
    }
    @Test
    public void sqltime(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}