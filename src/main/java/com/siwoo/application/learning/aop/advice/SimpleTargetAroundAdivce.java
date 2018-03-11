package com.siwoo.application.learning.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleTargetAroundAdivce implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("Greeting ");
        Object result = invocation.proceed();
        System.out.println(" ~ !");
        return result;
    }
}
