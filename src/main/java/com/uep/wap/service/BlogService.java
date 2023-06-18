package com.uep.wap.service;

import com.uep.wap.dto.BlogDTO;
import com.uep.wap.model.Blog;
import com.uep.wap.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public static void save(Blog blog) {

    }
}
