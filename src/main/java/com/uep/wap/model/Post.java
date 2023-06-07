package com.uep.wap.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    //tu jest klasa java.sql.Date
    @Column(name = "time")
    private Date time;


    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}