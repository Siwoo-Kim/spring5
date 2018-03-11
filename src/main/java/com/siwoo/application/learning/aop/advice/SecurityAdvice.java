package com.siwoo.application.learning.aop.advice;

import com.siwoo.application.learning.aop.SecurityManager;
import com.siwoo.application.learning.aop.UserInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.Assert;
import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice{
    private SecurityManager securityManager;

    public SecurityAdvice() {
        securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before calling method : '"+method.getName()+"' ");
        UserInfo user = securityManager.getUser();
        Assert.notNull(user,"User is not login, fail to execute '"+method.getName()+"'");
        if(!"siwoo".equals(user.getUsername())){
            throw new SecurityException("Authentication failed");
        }
        System.out.println("Greeting : "+user.getUsername());
    }
}
