package com.example.springBootTest;

import com.example.springBootTest.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by JieChen on 2018/5/14.
 */
@Configuration //宣告為spring 的 config class 相當於以前的xml
@ComponentScan(basePackages = "com.example.springBootTest") //掃描的packege
public class SpringCofig {
    @Bean   //通過註解表明是Bean
    public UserDao getUserDao(){
        return new UserDao();
    }
}
