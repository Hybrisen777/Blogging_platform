package com.uep.wap.config;

import com.uep.wap.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.uep.wap.service.PostService;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
     @Autowired
     private PostService postService;
     @Override
    public void run(String... args) throws Exception {
         List<Post> posts = postService.getAllPosts();

         if (posts.size() == 0) {
             Post post1 = new Post();
             post1.setTitle("What is Lorem Ipsum?");
             post1.setContent("Lorem ipsum dolor sit amet");

             postService.createPost(post1);
         }
     }
}
