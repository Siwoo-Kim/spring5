package com.siwoo.application.learning.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Setter @Getter
public class TestEntity {
    @Id private long id;
}
