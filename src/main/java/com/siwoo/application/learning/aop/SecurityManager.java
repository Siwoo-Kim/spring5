package com.siwoo.application.learning.aop;

public class SecurityManager {
    private static ThreadLocal<UserInfo> user = new ThreadLocal<>();

    public void login(String username,String password){
        user.set( new UserInfo(username,password) );
    }

    public void logout(){
        user.remove();
    }

    public UserInfo getUser(){
        return user.get();
    }

}
