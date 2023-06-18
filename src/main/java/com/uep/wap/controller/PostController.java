package com.uep.wap.controller;

import com.uep.wap.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
    @Autowired
    private PostService postService;


}
