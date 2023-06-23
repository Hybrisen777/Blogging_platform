//package com.uep.wap.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.sql.Date;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name="blogs")
//public class Blog {
//
//
//    @Id
//    @Column(name ="blog_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @OneToOne(mappedBy = "blog", cascade = CascadeType.ALL)
//    private Account author;
//
//    @Column(name = "content")
//    private String content;
//
//    @OneToOne(mappedBy = "blog", cascade = CascadeType.ALL)
//    private Statistics statistics;
//
//    @Column(name = "categories")
//    private String category;
//
//    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
//    @Column(name = "comments")
//    private List<Comment> comments;
//
//    //tu jest klasa java.sql.Date
//    @Column(name = "time")
//    private Date time;
//}
