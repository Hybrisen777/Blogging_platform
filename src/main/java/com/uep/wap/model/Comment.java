package com.uep.wap.model;

import javax.persistence.*;
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long com_id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

}
