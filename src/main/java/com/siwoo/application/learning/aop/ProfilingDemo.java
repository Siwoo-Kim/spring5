package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.ProfilingInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getProxy(new ProxyFactory(),new WorkerBean(),new ProfilingInterceptor());
        bean.doWork(10000);
    }
}
