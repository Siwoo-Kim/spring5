package com.siwoo.application.learning.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name="instrument") @Table(name="instrument")
@Getter @Setter @EqualsAndHashCode(of={"name"})
public class JpaInstrumentEntity implements Serializable {

    @Id @Column(name="instrument_id")
    private String name;

    @ManyToMany
    @JoinTable(name="singer_instrument",
    joinColumns = @JoinColumn(name="instrument_id"),
    inverseJoinColumns = @JoinColumn(name="singer_id"))
    private Set<JpaSingerEntity> singers;
}
