package com.siwoo.applicationl.learning.jpa;

import com.siwoo.application.learning.jdbc.Album;
import com.siwoo.application.learning.jpa.config.JpaConfig;
import com.siwoo.application.learning.jpa.entity.JpaAlbumEntity;
import com.siwoo.application.learning.jpa.entity.JpaSingerEntity;
import com.siwoo.application.learning.jpa.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class) @Transactional
@ContextConfiguration(classes = JpaConfig.class)
public class TestSingerServiceImpl {

    @Autowired SingerService singerService;

    JpaSingerEntity fixture1;

    @Before public void setup(){
        fixture1 = new JpaSingerEntity();
        fixture1.setFirstName("Siwoo");
        fixture1.setLastName("Kim");
        fixture1.setBirthDate(LocalDate.of(1989,3,4));

        JpaAlbumEntity albumEntity1 = new JpaAlbumEntity();
        albumEntity1.setTitle("album1");
        albumEntity1.setReleaseDate(LocalDate.now());
        fixture1.addAlbum(albumEntity1);

        JpaAlbumEntity albumEntity2 = new JpaAlbumEntity ();
        albumEntity2.setTitle("album2");
        albumEntity2.setReleaseDate(LocalDate.now());
        fixture1.addAlbum(albumEntity2);
    }
    Consumer<Collection<?>> listEntity = entities -> {
        entities.stream().map(Object::toString).forEach(log::info);
    };

    @Test
    public void testFindAll(){
        assertEquals( singerService.findAll().size(),3 );
        listEntity.accept(singerService.findAll());
    }

    @Test
    public void testFindAllWithAlbum(){
        singerService.save(fixture1);

        List<JpaSingerEntity> singers = singerService.findAllWithAlbums();
        assertEquals(singers.size(),4);
        listEntity.accept(singers);
        singers.stream().filter(singer -> singer.getId() == fixture1.getId())
                .forEach(singer -> {
                    Set<JpaAlbumEntity> albums = singer.getAlbums();
                    assertEquals(albums.size(),2);
                    listEntity.accept(albums);
                });
    }

    @Test
    public void testFindById(){
        singerService.save(fixture1);

        JpaSingerEntity foundEntity = singerService.findById(fixture1.getId());
        assertNotNull(foundEntity);
        assertEquals(foundEntity.getAlbums().size(),2);

    }
}
