package com.siwoo.application.learning.aop.advice;

import com.siwoo.application.learning.aop.ExternalApiBean;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ApplicationRethrowingAdvice implements ThrowsAdvice{

    public static class ApplicationException extends RuntimeException{
        public ApplicationException() {
        }

        public ApplicationException(Throwable cause) {
            super(cause);
        }
    }
    public static class ApplicationDomainException extends ApplicationException{
        public ApplicationDomainException() {
        }

        public ApplicationDomainException(Throwable cause) {
            super(cause);
        }
    }

    public void afterThrowing(ExternalApiBean.LazyInitCrazyExcpetion e){
        System.out.println("Caught: "+e.getClass().getName());
        throw new ApplicationException(e);

    }
    public void afterThrowing(NullPointerException e){
        System.out.println("Caught: "+e.getClass().getName());
        throw new ApplicationDomainException(e);
    }
    public void afterThrowing(IllegalStateException e){
        System.out.println("Caught: "+e.getClass().getName());
        throw new ApplicationException(e);
    }

}
