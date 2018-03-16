package com.siwoo.application.learning.hibernate.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @ToString(exclude = "singers")
@Entity(name="instrument") @Table(name="instrument")
@EqualsAndHashCode(of="instrumentName")
public class InstrumentEntity {

    @Id @Column(name="instrument_id")
    private String instrumentName;

    @ManyToMany
    @JoinTable(name="singer_instrument",
    joinColumns = @JoinColumn(name="instrument_id"),
    inverseJoinColumns = @JoinColumn(name="singer_id"))
    private Set<SingerEntity> singers = new HashSet<>();
}
