package com.uep.wap.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    //TODO dokonczyc reszte klas

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments;

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
