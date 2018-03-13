package com.siwoo.application.learning.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("scannedProxyBeanExecutor")
@Setter @Getter
public class ProxyBeanExecutor {
    @Autowired(required = false)
    @Qualifier("scannedProxyTargetBean")
    ProxyTarget proxyTarget;

    public int execute(){
        ProxyTargetBean.ArgumentBean argumentBean = new ProxyTargetBean.ArgumentBean();
        proxyTarget.greeting();
        argumentBean.setName("siwoo");
        proxyTarget.greeting(argumentBean);
        argumentBean.setName("kim");
        proxyTarget.hello();
        proxyTarget.greeting(argumentBean);
        return  proxyTarget.getNumber();
    }

}
