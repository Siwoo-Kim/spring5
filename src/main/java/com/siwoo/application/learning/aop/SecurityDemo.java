package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SecurityAdvice;
import org.springframework.aop.framework.ProxyFactory;
import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class SecurityDemo {

    public static void main(String[] args) {
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getProxy(new ProxyFactory(),new SecureBean(),new SecurityAdvice());

        mgr.login("siwoo","1234");
        bean.writeSecureMessage();
        mgr.logout();

        try {
            mgr.login("invalid user","1234");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }finally {
            mgr.logout();
        }

        try{
            bean.writeSecureMessage();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
