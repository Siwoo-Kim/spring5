package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.ApplicationRethrowingAdvice;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class ThrowsAdviceDemo {

    public static void main(String[] args) {
        ExternalApiBean bean = getProxy(new ProxyFactory(),new ExternalApiBean(),new ApplicationRethrowingAdvice());
        try {
            bean.errorGeneralProneTask();
        } catch (ApplicationRethrowingAdvice.ApplicationException e) {
            System.out.println(e.getMessage());
        }

        try {
            bean.errorProneTask1();
        } catch (ApplicationRethrowingAdvice.ApplicationException e) {
            System.out.println(e.getMessage());
        }

        try {
            bean.errorProneTask2();
        } catch (ApplicationRethrowingAdvice.ApplicationException e) {
            System.out.println(e.getMessage());
        }
    }
}
