package com.example.demo.business;

import com.example.demo.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Created by JieChen on 2018/5/28.
 */
//宣告他是AOP
@Order(1)//代表優先級最高
@Aspect
@Component
public class LogAspect {

    // pointCut
    @Pointcut("execution(@com.example.demo.annotation.Action * *(..))")
    //@Pointcut("@annotation(com.example.demo.annotation.Action)")
    public void log() {
        System.out.println("---beging....------------------------------.");
    }

    //前通知
    @Before("log()")
    public void beforeMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("action名称 " + action.value() + "  -參數" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "log()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        System.out.println("return is:" + retValue);
    }

    /**
     * 異常通知
     */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void afterThrowning(JoinPoint joinPoint, Exception e) {
        String method = joinPoint.getSignature().getName();
        System.out.println("action名称 " + method + "  -參數" + e.toString());
    }


    /**
     * 環繞通知
     */
    /*
    @Around("execution(* com.example.demo.api.*.*(..))")
    public void aroundApi(ProceedingJoinPoint pjp) throws Throwable {
        //=前置通知
        System.out.println("action名称 " + pjp.getSignature().getName() + "  -參數" + Arrays.asList(pjp.getArgs()));
        //執行目標方法
        Object res = pjp.proceed();
        //=後置通知
        System.out.println("action名称 " + pjp.getSignature().getName() + "  -return is" + res);

    }
*/
}
