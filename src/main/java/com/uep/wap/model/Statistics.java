package com.uep.wap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "likes")
    private int likes;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

}
