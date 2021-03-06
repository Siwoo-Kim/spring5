package com.siwoo.application.learning.aop.declartive.adivce;

import com.siwoo.application.learning.common.ProxyTargetBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdivce {

    public Object aroundAudit(ProceedingJoinPoint pjp, ProxyTargetBean.ArgumentBean value) throws Throwable {
        if(value.getName() != null && "kim".equals(value.getName())){
            Signature signature = pjp.getSignature();
            System.out.println("Around Before '"+signature.getDeclaringType()+"' of method '"
                +signature.getName()+"' "
                    +"argu : "+value
            );

            Object result = pjp.proceed();
            System.out.println("Around After '"+signature.getDeclaringType()+"' of method '"
                    +signature.getName()+"' "
                    +"argu : "+value
            );
            return result;
        }
        return pjp.proceed();
    }

    public void audit(JoinPoint joinPoint, ProxyTargetBean.ArgumentBean value){
        Signature signature = joinPoint.getSignature();
        System.out.println(
                signature.getDeclaringTypeName()+", of method : "
                +signature.getName()+" invoked");
        System.out.println("Args("+value+")");
    }
    /*
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("Method invoked");
        }
    */
}
