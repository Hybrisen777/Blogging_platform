package com.uep.wap.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
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
    private User author;

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

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @Column(name = "posts")
    private List<Post> posts;


}
