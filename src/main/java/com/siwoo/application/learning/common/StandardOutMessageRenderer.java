package com.siwoo.application.learning.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("scanedMessageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer{
    @Autowired(required = false) /*@Qualifier("scanedMessageProvider")*/
    @Qualifier("scanedConfigurableMessageProvider")
    private MessageProvider messageProvider;

    @Override
    public void render() {
        Assert.notNull(messageProvider,"Provider :"+messageProvider.getClass().getName()+" must not null");
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
