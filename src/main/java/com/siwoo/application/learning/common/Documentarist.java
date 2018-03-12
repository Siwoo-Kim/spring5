package com.siwoo.application.learning.common;

import lombok.Getter;
import lombok.Setter;

public class Documentarist {
    private GrammyGuitarist guitarist;

    public void execute(){
        guitarist.sing();
        guitarist.talk();
    }

    public GrammyGuitarist getGuitarist() {
        return guitarist;
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

}
