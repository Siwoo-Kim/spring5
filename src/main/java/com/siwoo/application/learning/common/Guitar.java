package com.siwoo.application.learning.common;

public class Guitar {
    static StringBuilder stringBuilder;
    static {
        stringBuilder = new StringBuilder();
        stringBuilder.append("G C G C Am D7");
    }
    public String play(){
        return stringBuilder.toString();
    }
}
