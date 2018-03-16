package com.siwoo.application.learning.hibernate.repository;

import com.siwoo.application.learning.hibernate.entity.InstrumentEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("hibernateInstrumentRepository") @Transactional(readOnly = true)
public class HibernateInstrumentRepository implements InstrumentRepository {

    @Autowired SessionFactory sessionFactory;

    @Override @Transactional(readOnly = false)
    public InstrumentEntity save(InstrumentEntity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}
