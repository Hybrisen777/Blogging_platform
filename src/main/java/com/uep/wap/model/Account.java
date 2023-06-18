package com.uep.wap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    //TODO dokonczyc reszte klas

//    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
//    private List<Comment> comments;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

}
