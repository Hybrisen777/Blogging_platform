package com.uep.wap.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int views = 0;

    //TODO dodac obiekt z użytkownikiem i postem oraz booleanem czy like czy nie,
    // który bedzie dodawany do listy, na podstawie której będzie liczona ilość likeów
    @Column(nullable = false)
    private int likes = 0;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", title='" + tag + "'" +
                ", body='" + content + "'" +
                ", createdAt='" + createdAt + "'" +
                ", updatedAt='" + updatedAt + "'" +
                "}";
    }



    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable=false)
    private Account account;


    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


}
