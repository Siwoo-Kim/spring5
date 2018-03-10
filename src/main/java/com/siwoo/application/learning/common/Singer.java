package com.siwoo.application.learning.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component("singer") @ToString @Setter @Getter
public class Singer {
    private static final String DEFAULT_NAME = "이효리";
    private String name;
    private int age = Integer.MIN_VALUE;


//    public void init(){
//        System.out.println("Initializing bean");
//        if(name == null){
//            System.out.println("Using default name");
//            name = DEFAULT_NAME;
//        }
//        //Assert.isTrue(age == Integer.MIN_VALUE, "Must set the age : "+Singer.class);
//        if(age == Integer.MIN_VALUE){
//            //throw new IllegalStateException("Must set the age");
//            log.warn("age is not defined!");
//        }
//    }

    public void app_init(){
        System.out.println("Initializing bean");
        if(name == null){
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        //Assert.isTrue(age == Integer.MIN_VALUE, "Must set the age : "+Singer.class);
        if(age == Integer.MIN_VALUE){
            //throw new IllegalStateException("Must set the age");
            log.warn("age is not defined!");
        }
    }


        public void sing(){
        //System.out.println("..." + inspirationBean.getLyric());
        //System.out.println(lyric);
    }


    @Autowired(required = false) private Inspiration inspirationBean;
    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

}
