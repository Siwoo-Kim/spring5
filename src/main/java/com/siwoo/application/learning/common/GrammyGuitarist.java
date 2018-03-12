package com.siwoo.application.learning.common;

import com.siwoo.application.learning.aop.pointcut.Adviced;

public class GrammyGuitarist extends Singer {

    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me");
    }

    @Adviced
    public void talk(){
        System.out.println("Blur blur");
    }
}
