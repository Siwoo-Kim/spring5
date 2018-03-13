package com.siwoo.application.learning.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component("scannedProxyTargetBean")
public class ProxyTargetBean implements ProxyTarget {
    @Override
    public void greeting() {
        System.out.println("Greeting Spring 5!");
    }

    @Override
    public void greeting(ArgumentBean argumentBean) {
        System.out.println("Greeting Spring 5!");
    }

    @Override
    public void hello() {
        System.out.println("Hello!");
    }

    Random random = new Random();

    @Override
    public int getNumber() {
        return random.nextInt(3);
    }

    public static class ArgumentBean{
        private String name;
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
    }
}
