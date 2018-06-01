package com.example.springBootTest.business;

import com.example.springBootTest.dao.UserDao;
import com.example.springBootTest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by JieChen on 2018/5/14.
 */

public interface UserService {
    ArrayList<User> queryUserList();
    ArrayList<User> queryUserListOrderDesc();
}
