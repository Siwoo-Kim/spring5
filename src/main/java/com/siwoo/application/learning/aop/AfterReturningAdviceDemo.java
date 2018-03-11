package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAfterReturningAdvice;
import com.siwoo.application.learning.common.Guitarist;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class AfterReturningAdviceDemo {
    public static void main(String[] args) {
        Guitarist guitarist = getProxy(new ProxyFactory(),new Guitarist(),new SimpleAfterReturningAdvice());
        guitarist.sing();
    }
}
