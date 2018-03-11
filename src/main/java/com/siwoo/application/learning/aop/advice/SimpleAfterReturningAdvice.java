package com.siwoo.application.learning.aop.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

        if(!ObjectUtils.isEmpty(returnValue)){
            System.out.println("Method '"+method.getName()+"' return the value of '"+returnValue+"'");
        }else{
            System.out.println("Method '"+method.getName()+"' return and does return no value");
        }

    }
}
