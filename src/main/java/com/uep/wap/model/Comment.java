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
    private User user;

    //TODO dokonczyc reszte klas
    //jakos pomyslec z autorem komentarza

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void setCom_id(Long id) {
        this.com_id = id;
    }
    public Long getCom_id() {
        return com_id;
    }
}
