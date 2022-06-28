package com.space.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void selectAll() {
        UserServiceImpl service = new UserServiceImpl();
        service.selectAll();
    }
}