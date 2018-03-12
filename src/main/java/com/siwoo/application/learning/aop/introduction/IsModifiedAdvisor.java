package com.siwoo.application.learning.aop.introduction;

import org.aopalliance.aop.Advice;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

    public IsModifiedAdvisor() {
        super(new IsModifiedIntroduction());
    }
}
