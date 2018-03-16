package com.siwoo.application.learning.hibernate.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;


@Getter @Setter @ToString(exclude = "singer")
@Entity(name="album") @Table(name="album")
@EqualsAndHashCode(of={"id","title"})
public class AlbumEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="singer_id")
    private SingerEntity singer;

    private String title;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @Version
    private int version;
}
