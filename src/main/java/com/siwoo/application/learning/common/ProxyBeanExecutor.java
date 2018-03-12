package com.siwoo.application.learning.common;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProxyBeanExecutor {
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
