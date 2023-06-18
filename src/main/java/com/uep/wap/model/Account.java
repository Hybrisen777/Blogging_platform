package com.uep.wap.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //TODO dokonczyc reszte klas

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

}
