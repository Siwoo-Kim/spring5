package com.siwoo.application.learning.aop.advice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before '"+method.getName()+"' is called");

        if(!ObjectUtils.isEmpty(args)){
            System.out.printf("Argument : ");
            for(Object arg: args){
                System.out.printf(arg+", ");
            }
        }
    }
}
