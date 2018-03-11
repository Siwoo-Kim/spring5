package com.siwoo.application.learning.aop.advice;

import com.siwoo.application.learning.aop.KeyGenerator;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WeakKeyCheckAfterReturningAdvice implements AfterReturningAdvice{
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if(checkJointpoint(method,target)){
            KeyGenerator keyGenerator = (KeyGenerator) target;
            long keyValue  = (Long) returnValue;
            if (keyGenerator.getWEAK_KEY() == keyValue){
                throw new SecurityException("KeyGenerator generated weak key");
            }
        }
    }

    private static boolean checkJointpoint(Method method, Object target) {
        return "generateKey".equals(method.getName()) && target instanceof KeyGenerator;
    }
}
