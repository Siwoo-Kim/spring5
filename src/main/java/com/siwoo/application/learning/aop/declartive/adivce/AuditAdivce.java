package com.siwoo.application.learning.aop.declartive.adivce;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdivce {

    public void audit(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println(
                signature.getDeclaringTypeName()+", of method : "
                +signature.getName()+" invoked");
    }
    /*
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("Method invoked");
        }
    */
}
