package com.uep.wap.model;

import javax.persistence.*;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "likes")
    private int likes;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
