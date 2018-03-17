package com.siwoo.application.learning.jpa.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name="album") @Table(name="album") @ToString
@Getter @Setter @EqualsAndHashCode(of={"id","title","releaseDate"})
public class JpaAlbumEntity implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private String title;
    @Column(name="release_date")
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name="singer_id") //FK SINGER_ID
    private JpaSingerEntity singer;

}
