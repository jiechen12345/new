package com.example.springBootTest.business.impl;

import com.example.springBootTest.business.UserService;
import com.example.springBootTest.dao.UserDao;
import com.example.springBootTest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by JieChen on 2018/5/22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao=new UserDao();

    @Override
    public ArrayList<User> queryUserList() {
        System.out.println("111111111111111111111");
        return userDao.queryUser();
    }

    @Override
    public ArrayList<User> queryUserListOrderDesc() {
        System.out.println("2222222222222222222222");
        return userDao.queryUserOrderDesc();
    }
}
