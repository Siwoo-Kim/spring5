package com.siwoo.application.learning.aop;

public class ExternalApiBean {
    public static class LazyInitCrazyExcpetion extends RuntimeException{}
    public void errorProneTask1(){
        throw new LazyInitCrazyExcpetion();
    }
    public void errorProneTask2(){
        throw new NullPointerException();
    }
    public void errorGeneralProneTask(){
        throw new IllegalStateException();
    }
}
