package com.siwoo.application.learning.jpa.service;

import com.siwoo.application.learning.jpa.entity.JpaSingerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service @Repository //to translate DataException to more recognizable exception
@Transactional(readOnly = true)
public class SingerServiceImpl implements SingerService {

    @PersistenceContext EntityManager entityManager;
    private static Class domainClass = JpaSingerEntity.class;

    @Override
    public List<JpaSingerEntity> findAll() {
        return entityManager.createNamedQuery(JpaSingerEntity.NAMEDQUERY_FINDALL,domainClass)
                .getResultList();
    }

    @Override
    public List<JpaSingerEntity> findAllWithAlbums() {
        return entityManager.createNamedQuery(JpaSingerEntity.NAMEDQUERY_FINDALL_WITH_ALBUM,domainClass)
                .getResultList();
    }

    @Override
    public JpaSingerEntity findById(Long id) {
        TypedQuery<JpaSingerEntity> query = entityManager
                .createNamedQuery(JpaSingerEntity.NAMEDQUERY_FIND_BY_ID,domainClass);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override @Transactional(readOnly = false)
    public JpaSingerEntity save(JpaSingerEntity entity) {
        Assert.notNull(entity,"entity must not be null");
        entityManager.persist(entity);
        return entity;
    }

    @Override @Transactional(readOnly = false)
    public void delete(JpaSingerEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<JpaSingerEntity> findAllByNativeQuery() {
        throw new UnsupportedOperationException();
    }
}
