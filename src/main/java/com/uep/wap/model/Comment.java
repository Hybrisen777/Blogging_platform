package com.uep.wap.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {


    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable=false)
    private Account account;

    public Comment() {
    }



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long com_id;
//
//    @Column(name = "description")
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    private Account account;
//
//
//    @ManyToOne
//    @JoinColumn(name = "blog_id")
//    private Blog blog;

}
