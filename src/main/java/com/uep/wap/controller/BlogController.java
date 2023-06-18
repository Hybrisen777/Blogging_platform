package com.uep.wap.controller;


import com.uep.wap.dto.BlogDTO;
import com.uep.wap.dto.StudentDTO;
import com.uep.wap.service.BlogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class BlogController {

    private final BlogService blogService;


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path = "/blog")
    public String showBlog(){

        return
                /*blogService.getAllBlogs().toString() + */"xd";
    }

    @PostMapping(path = "/blogs")
    public String addBlog(@RequestBody BlogDTO blogDTO){
        blogService.addBlog(blogDTO);
        return "Students added!";
    }




}


