package com.siwoo.application.learning.aop;

public class WorkerBean {

    public void doWork(int times) {
        for(int i=0;i<times;i++){
            work(". ");
            if(i % 20 == 0){
                System.out.println();
            }
        }
    }

    private void work(String arg) {
        System.out.printf(arg);
    }
}
