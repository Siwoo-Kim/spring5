package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.WeakKeyCheckAfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class WeakKeyCheckAfterReturningAdviceDemo {

    public static void main(String[] args) {
        KeyGenerator keyGenerator = getProxy(new ProxyFactory(),new KeyGenerator(),new WeakKeyCheckAfterReturningAdvice());

        for(int i=0;i<10;i++){
            try {
                System.out.println(keyGenerator.generateKey());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
