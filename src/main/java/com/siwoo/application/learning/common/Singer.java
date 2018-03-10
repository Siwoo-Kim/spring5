package com.siwoo.application.learning.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
public class Singer {

    @Autowired(required = false) private Inspiration inspirationBean;
    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing(){
        //System.out.println("..." + inspirationBean.getLyric());
        //System.out.println(lyric);
    }

}
