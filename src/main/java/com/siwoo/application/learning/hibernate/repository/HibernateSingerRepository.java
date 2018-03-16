package com.siwoo.application.learning.hibernate.repository;

import com.siwoo.application.learning.hibernate.entity.AlbumEntity;
import com.siwoo.application.learning.hibernate.entity.SingerEntity;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true) @Service("hibernateSingerRepository")
public class HibernateSingerRepository implements SingerRepository<SingerEntity> {

    @Autowired SessionFactory sessionFactory;

    @Override
    public List<SingerEntity> findAll() {
        /*get current connection in the transaction*/
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s")
                .list();
    }

    @Override
    public List<SingerEntity> findAllWithAlbums() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findAllWithAlbums")
                .list();
    }

    @Override
    public SingerEntity findById(Long id) {
        return (SingerEntity) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id",id)
                .uniqueResult();
    }

    @Override
    public List<SingerEntity> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findFullNameById(Long id) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String findFirstNameById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override @Transactional(readOnly = false)
    public void save(SingerEntity singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        log.info(singer.getId()+ " Singer is saved");
        if(!singer.getAlbums().isEmpty()){
            singer.getAlbums().stream()
                    .map(AlbumEntity::getId)
                    .forEach(albumId -> log.info(albumId + " Album is saved"));
        }
    }

    @Override @Transactional(readOnly = false)
    public void saveWithAlbum(SingerEntity singer) {
        save(singer);
    }

    @Override
    public boolean update(SingerEntity singer) {
        throw new UnsupportedOperationException();
    }

    /* delete with assocations */
    @Override
    public void delete(Long singerId) {
        sessionFactory.getCurrentSession().delete(findById(singerId));
    }


    @Override
    public void insertWithDetail(SingerEntity singer) {
        throw new UnsupportedOperationException();
    }
}
