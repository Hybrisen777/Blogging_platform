package com.uep.wap.model;


import javax.persistence.*;
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    //TODO dokonczyc reszte klas

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
