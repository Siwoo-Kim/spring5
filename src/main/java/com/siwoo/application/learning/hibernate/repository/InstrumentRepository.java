package com.siwoo.application.learning.hibernate.repository;

import com.siwoo.application.learning.hibernate.entity.InstrumentEntity;
import org.springframework.stereotype.Repository;

public interface InstrumentRepository {

    InstrumentEntity save(InstrumentEntity entity);

}
