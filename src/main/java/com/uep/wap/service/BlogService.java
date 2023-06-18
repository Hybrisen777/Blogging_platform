package com.uep.wap.service;

import com.uep.wap.dto.BlogDTO;
import com.uep.wap.dto.StudentDTO;
import com.uep.wap.model.Blog;
import com.uep.wap.model.Student;
import com.uep.wap.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public void addBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setContent(blogDTO.getContent());
        blogRepository.save(blog);
        System.out.println("Blog added!");
    }

//    public Iterable<Blog> getAllBlogs() {
//        return blogRepository.findAll();
//    }
}
