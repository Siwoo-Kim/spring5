package com.siwoo.application.learning.aop;

import lombok.Getter;

@Getter
public class UserInfo {
    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
