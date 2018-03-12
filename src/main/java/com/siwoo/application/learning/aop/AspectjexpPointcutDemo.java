package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.common.GrammyGuitarist;
import com.siwoo.application.learning.common.Guitar;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class AspectjexpPointcutDemo {

    public static void main(String[] args) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //pointcut.setExpression("execution(void *.sing*(..))");
        pointcut.setExpression("execution(void *.sing*(com.siwoo.application.learning.common.Guitar))");
        GrammyGuitarist singer = getProxy(new GrammyGuitarist(),new SimpleAdvice(),pointcut);

        singer.sing();
        singer.sing(new Guitar());
        singer.sing2();
        singer.rest();
        ((GrammyGuitarist)singer).talk();
    }
}
