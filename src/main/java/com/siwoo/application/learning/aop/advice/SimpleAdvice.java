package com.siwoo.application.learning.aop.advice;

import com.siwoo.application.learning.common.GreatGuitarist;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> clazz = invocation.getThis().getClass();
        //There is no need for filtering method by name or arg, Because of pointcut
        System.out.println("Proceeding method of '"+
                invocation.getMethod().getName()
                +"' in '"+
                clazz.getSimpleName()
                +"'");
        //Assert.state(clazz == GreatGuitarist.class,"Pointcut must accept only "+clazz.getSimpleName());

        Object result = invocation.proceed();

        System.out.println("Ending method");
        return result;
    }
}
