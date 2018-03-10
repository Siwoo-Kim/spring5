package com.siwoo.application.learning.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component @Getter @Setter
public class Inspiration {
    private String lyric ="I can keep the door cracked open, to let light through";

    public Inspiration(@Value("For all my running,I can understand") String lyric) {
        this.lyric = lyric;
    }

}
