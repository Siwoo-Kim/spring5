package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.Song;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

public class HierarchicalAppContextUsage {

    public static void main(String[] args) {
        ApplicationContext parent = new GenericXmlApplicationContext(new ClassPathResource("spring/ioc/parent-context.xml"));
        GenericXmlApplicationContext child = new GenericXmlApplicationContext();
        child.setParent(parent);
        child.load(new ClassPathResource("spring/ioc/child-context.xml"));
        child.refresh();

        Song song1 = child.getBean("song1",Song.class);
        Song song2 = child.getBean("song2",Song.class);
        Song song3 = child.getBean("song3",Song.class);

        Assert.isTrue("별이지는밤에".equals(song1.getTitle()));
        Assert.isTrue("노을".equals(song2.getTitle()));
        Assert.isTrue("북극성".equals(song3.getTitle()));
    }
}
