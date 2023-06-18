package com.uep.wap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="blogs")
public class Blog {
    private Long blog_id;

    private void setBlog_id(Long id) {
        this.blog_id = id;
    }

    @Id
    @Column(name ="blog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long getBlog_id() {
        return blog_id;
    }

    @Column(name = "author")
    @OneToOne(mappedBy = "blog", cascade = CascadeType.ALL)
    private Account author;

    @Column(name = "content")
    private String content;

    @Column(name = "statistics")
    @OneToOne(mappedBy = "blog", cascade = CascadeType.ALL)
    private Statistics statistics;

    @Column(name = "categories")
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @Column(name = "comments")
    private List<Comment> comments;

    //tu jest klasa java.sql.Date
    @Column(name = "time")
    private Date time;
}
