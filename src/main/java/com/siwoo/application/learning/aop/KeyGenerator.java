package com.siwoo.application.learning.aop;

import lombok.Getter;

import java.util.Random;

public class KeyGenerator {
    @Getter
    private long WEAK_KEY = 111111111111111L;
    private long STRONG_KEY = 0x129ADCDE0B29344L;

    private Random random = new Random();

    public long generateKey(){
        if(random.nextInt(3) == 0){
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }

}
