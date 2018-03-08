package com.siwoo.application.learning.common;

import org.springframework.stereotype.Component;

@Component("scanedMessageProvider")
public class HellowordMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "hello spring 5!";
    }
}
