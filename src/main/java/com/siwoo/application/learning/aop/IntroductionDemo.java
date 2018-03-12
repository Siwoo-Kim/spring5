package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.introduction.IntroTarget;
import com.siwoo.application.learning.aop.introduction.IsModified;
import com.siwoo.application.learning.aop.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class IntroductionDemo {
    public static void main(String[] args) {
        IntroTarget target = new IntroTarget();
        target.setName("Siwoo");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();
        IntroTarget proxy = getProxy(target,advisor);

        IsModified proxyInterface = (IsModified)proxy;

        System.out.println("Is Contact?: "+(proxy instanceof IntroTarget));
        System.out.println("Is IsModified?: "+(proxy instanceof IsModified));
        System.out.println("Has bean modified?: "+proxyInterface.isModified());
        //proxy.setName("Kim siwoo");
        proxy.setName("Siwoo");
        System.out.println("Has bean modified?: "+proxyInterface.isModified());
        proxy.setPhoneNumber("1111");
        System.out.println("Has bean modified?: "+proxyInterface.isModified());
    }

    private static <T> T getProxy(T target, Advisor advisor) {
        ProxyFactory px = new ProxyFactory();
        px.setTarget(target);
        px.setOptimize(true);
        px.addAdvisor(advisor);
        return (T) px.getProxy();
    }
}
