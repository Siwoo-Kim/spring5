package com.siwoo.applicationl.learning.hibernate;

import com.siwoo.application.learning.hibernate.config.HibernateConfig;
import com.siwoo.application.learning.hibernate.entity.AlbumEntity;
import com.siwoo.application.learning.hibernate.entity.SingerEntity;
import com.siwoo.application.learning.jdbc.Singer;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class TestHibernateSingerRepository {


    @Autowired SingerRepository hibernateSingerRepository;

//    Consumer<List<SingerEntity>> listSingers = singers -> {
//        singers.stream().map(SingerEntity::toString).forEach(log::info);
//    };
    Consumer<Collection<?>> listEntities = entities -> {
        entities.stream().map(Object::toString).forEach(log::info);
    };

    SingerEntity fixture1;

    @Before
    public void setup(){
        fixture1 = new SingerEntity();
        fixture1.setFirstName("Siwoo");
        fixture1.setLastName("Kim");
        fixture1.setBirthDate(LocalDate.of(1989,3,4));

        AlbumEntity albumEntity1 = new AlbumEntity();
        albumEntity1.setTitle("album1");
        albumEntity1.setReleaseDate(LocalDate.now());
        fixture1.addAlbum(albumEntity1);

        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setTitle("album2");
        albumEntity2.setReleaseDate(LocalDate.now());
        fixture1.addAlbum(albumEntity2);
    }

    @Test
    public void testFindAll(){
        List<SingerEntity> singerList = hibernateSingerRepository.findAll();
        assertNotNull(singerList);
        assertEquals(singerList.size(),3);
        listEntities.accept(singerList);
    }

    @Test
    public void testFindAllWithAlbums(){
        List<SingerEntity> singerList = hibernateSingerRepository.findAllWithAlbums();
        assertNotNull(singerList);
        assertEquals(singerList.size(),3);

        listEntities.accept(singerList);
        for(SingerEntity singer : singerList){
            System.out.println(singer.getFirstName()+"'s album ==");
            listEntities.accept(singer.getAlbums());
        }
    }


    @Test
    public void testFindById(){
        SingerEntity singer = hibernateSingerRepository.findById(1l);
        assertNotNull(singer);
        listEntities.accept(Arrays.asList(singer));
    }

    @Test @Transactional
    public void testSave(){
        hibernateSingerRepository.save(fixture1);
        assertNotNull(fixture1.getId());
        assertEquals(hibernateSingerRepository.findAll().size(),4);
        listEntities.accept(hibernateSingerRepository.findAll());

        SingerEntity foundEntity = hibernateSingerRepository.findById(fixture1.getId());
        assertEquals(foundEntity.getAlbums().size(),2);

        hibernateSingerRepository.delete(foundEntity.getId());
        assertEquals(hibernateSingerRepository.findAll().size(),3);
        listEntities.accept(hibernateSingerRepository.findAll());

    }
}
