package com.siwoo.application.learning.aop.declartive.adivce;

import com.siwoo.application.learning.common.ProxyTargetBean;
import org.aspectj.lang.JoinPoint;

public class ComplexAdivce {

    public void before(JoinPoint joinPoint, ProxyTargetBean.ArgumentBean arg){
        if("kim".equals(arg.getName())){
            System.out.println("Executing : "+joinPoint.getSignature().getName());
        }
    }

}

