package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleBeforeAdvice;
import com.siwoo.application.learning.common.Guitarist;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class MethodBeforeAdviceDemo {
    public static void main(String[] args) {
        ProxyFactory px = new ProxyFactory();
        Guitarist guitarist = new Guitarist();
        Guitarist proxy = getProxy(px,guitarist,new SimpleBeforeAdvice());
        proxy.sing();
    }


}
