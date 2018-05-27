package com.example.springBootTest.dao;

import com.example.springBootTest.entity.User;
import javafx.beans.binding.ListBinding;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JieChen on 2018/5/14.
 */
public class UserDao {
    public UserDao() {
    }

    public ArrayList<User> queryUser() {

        //模擬撈資料庫
        ArrayList<User> result = new ArrayList<User>();
        for(int i=0;i<=5;i++) {
            User user=new User("userName"+i,"userPassword"+i,i);
            result.add(user);
        }

        return result;
    }

    public ArrayList<User> queryUserOrderDesc() {

        //模擬撈資料庫
        ArrayList<User> result = new ArrayList<User>();
        for(int i=5;i>=0;i--) {
            User user=new User("userName"+i,"userPassword"+i,i);
            result.add(user);
        }

        return result;
    }


}