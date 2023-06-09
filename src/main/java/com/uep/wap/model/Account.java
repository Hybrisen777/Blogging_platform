package com.uep.wap.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "User's name cannot be empty.")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "User's password cannot be empty.")
    @Column(name = "password")
    private String password;

    //TODO dokonczyc reszte klas

    @OneToMany(mappedBy = "account")
    private List<Comment> comments;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

//    @OneToOne
//    @JoinColumn(name = "blog_id")
//    private Blog blog;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_authority",
        joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Account{" +
            ", username='" + username + "'" +
            ", password='" + password + "'" +
            ", authorities=" + authorities +
        "}";
    }

}
