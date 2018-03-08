package com.siwoo.application.learning.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("scanedConfigurableMessageProvider")
public class ConfigurableMessageProvider implements MessageProvider{

    private String message;

    @Autowired @Qualifier("anotherMessage")
    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        Assert.hasText(message,"Message is not configured");
        return message;
    }
}
