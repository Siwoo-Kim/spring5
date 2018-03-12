package com.siwoo.application.learning.common;

public interface ProxyTarget {


    void greeting();
    void greeting(ProxyTargetBean.ArgumentBean argumentBean);
    void hello();
    int getNumber();

}
