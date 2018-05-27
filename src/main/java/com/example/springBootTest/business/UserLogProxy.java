package com.example.springBootTest.business;


import javafx.application.Application;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by JieChen on 2018/5/22.
 */
//AOP
public class UserLogProxy {
    //要代理的對象
    private UserService target;

    public UserLogProxy(UserService target) {
        this.target = target;
    }

    public UserService getLogProxy() {
        UserService proxy = null;
        //類加載器
        ClassLoader loader = target.getClass().getClassLoader();
        // 代理對象的類型
        Class[] interfaces = new Class[]{UserService.class};
        //當調用代理對象其中的方法時,執行這段代碼
        InvocationHandler h = new InvocationHandler() {
        //            正在返回哪個代理對象
        //            正在被調用的方法
        //            傳入的參數
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("...."+method.getName());
                Object r=method.invoke(target,args);
                System.out.println(r.toString());
                return r;
            }
        };
        proxy = (UserService) Proxy.newProxyInstance(loader,interfaces,h);

        return proxy;
    }
}
