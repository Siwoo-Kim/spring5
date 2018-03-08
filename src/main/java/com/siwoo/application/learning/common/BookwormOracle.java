package com.siwoo.application.learning.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("scanedOracle")
public class BookwormOracle implements Oracle {
    private Encyclopedia encyclopedia;

    public void setEncyclopedia(Encyclopedia encyclopedia){
        this.encyclopedia = encyclopedia;
    }
    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - go see the world instead";
    }
}
