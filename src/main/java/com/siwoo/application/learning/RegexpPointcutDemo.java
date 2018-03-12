package com.siwoo.application.learning;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.common.GrammyGuitarist;
import com.siwoo.application.learning.common.Guitar;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class RegexpPointcutDemo {
    public static void main(String[] args) {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing[2]?.*");
        GrammyGuitarist singer = getProxy(new GrammyGuitarist(),new SimpleAdvice(),pointcut);

        singer.sing();
        singer.sing(new Guitar());
        singer.sing2();
        singer.rest();
        ((GrammyGuitarist)singer).talk();
    }
}
