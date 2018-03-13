package com.siwoo.application.learning.aop.declartive.aspectj;

import com.siwoo.application.learning.common.ProxyTargetBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotatedAuditAdivce {

    @Pointcut("execution(void *..ProxyT*.greeting*(*..ArgumentBean*)) " +
            " && args(value) ")
    public void aspectjGreetingWithArgPointcut(ProxyTargetBean.ArgumentBean value){}

    @Pointcut("bean(scanned*)")
    public void aspectjScannedBeanPointcut(){};

    @Around("aspectjGreetingWithArgPointcut(value) && aspectjScannedBeanPointcut()")
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

    @Before("aspectjGreetingWithArgPointcut(value) && aspectjScannedBeanPointcut() ")
    public void audit(JoinPoint joinPoint, ProxyTargetBean.ArgumentBean value){
        Signature signature = joinPoint.getSignature();
        System.out.println(
                signature.getDeclaringTypeName()+", of method : "
                        +signature.getName()+" invoked");
        System.out.println("Args("+value+")");
    }
}
