package com.uep.wap.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    //TODO dokonczyc reszte klas

//    @ManyToOne
//    @JoinColumn(name = "blog_id")
//    private Blog blog;

}
