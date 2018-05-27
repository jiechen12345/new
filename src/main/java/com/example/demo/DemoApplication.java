package com.example.demo;

import com.example.springBootTest.SpringCofig;
import com.example.springBootTest.business.UserLogProxy;
import com.example.springBootTest.business.UserService;
import com.example.springBootTest.business.impl.UserServiceImpl;
import com.example.springBootTest.entity.User;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.util.ArrayList;

@SpringBootApplication//他其實是組合註解 裡面包含@Configuration @ComponentScan他會自動掃描同級或者子層的所有檔案
        (exclude = {RedisAutoConfiguration.class,MongoDataAutoConfiguration.class}) //排除某樣自動配置用exclude
@Controller //控制類?
public class DemoApplication {

    public static void main(String[] args) {
        //        spring 寫法
        //載入容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringCofig.class);
        UserService userService=new UserServiceImpl();
        UserService proxy= new UserLogProxy(userService).getLogProxy();
        proxy.queryUserList();
        proxy.queryUserListOrderDesc();
        /*
        //調用bean
        UserService userService = context.getBean(UserService.class);
        userService.queryUserList();
        userService.queryUserListOrderDesc();
        */
        /*
        for (User user : userService.queryUserList()) {
            System.out.println("** " + user.toString() + " ** ");
        }
        */
        //關掉spring容器
        context.destroy();

        //SpringApplication.run(DemoApplication.class, args);

/*        原始寫法
//        UserService userService = new UserService();
//        ArrayList<User> list = userService.queryUserList();
//        for (User user : list) {
//            System.out.println("** " + user.toString() + " ** ");
//        }
*/



    }
}
