package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

public class LookupMethodInjection {
    /*
        Getting an fully new non-singleton bean when the method is called.
        As an non-singleton which injected into singleton bean
        shares the life cycle with the singleton bean,
        We need Lookup Method injection mechanism get fully new instance
        every time the method is questioned
    */

    public interface DemoBean{
        Singer getMySinger();
        void doSomething();
    }

    public static class StandardLookupBean implements DemoBean{
        private Singer mySinger;
        public void setMySinger(Singer mySinger){
            this.mySinger = mySinger;
        }
        @Override
        public Singer getMySinger() {
            return mySinger;
        }

        @Override
        public void doSomething() {
            mySinger.sing();
        }
    }

    public static abstract class AbstractLookupBean implements DemoBean{
        @Override abstract public Singer getMySinger();
        @Override
        public void doSomething() {
            getMySinger().sing();
        }
    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/ioc-context.xml"));
        DemoBean abstractBean = c.getBean("abstractLookupBean",DemoBean.class);
        DemoBean standardBean = c.getBean("standardLookupBean",DemoBean.class);
        Assert.notNull(abstractBean,"Must not be null");
        Assert.notNull(standardBean,"Must not be null");

        displayInfo("abtractBean",abstractBean);
        displayInfo("standardBean",standardBean);
    }

    public static void displayInfo(String beanName, DemoBean bean){
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();
        System.out.println(beanName+" : Singer Instances the same? "+ (singer1==singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Method Lookup");
        for(int i=0;i<10000;i++){
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();
        System.out.println( stopWatch.prettyPrint() );
    }
}




























