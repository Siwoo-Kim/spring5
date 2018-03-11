package com.siwoo.application.learning.common;

public class Guitarist extends Singer {
    private static final String lyric = "너 때매 못살아~";
    @Override
    public void sing() {
        System.out.println(lyric);
    }
}
