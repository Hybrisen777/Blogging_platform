package com.uep.wap.config;

import com.uep.wap.model.Account;
import com.uep.wap.model.Post;
import com.uep.wap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.uep.wap.service.PostService;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
     @Autowired
     private PostService postService;
     @Autowired
     private AccountService accountService;
     @Override
    public void run(String... args) throws Exception {
         List<Post> posts = postService.getAllPosts();

         if (posts.size() == 0) {
             Account account1 = new Account();
             account1.setUsername("user");
             account1.setPassword("password");
             accountService.save(account1);

             Post post1 = new Post();
             post1.setTitle("EA Sports");
             post1.setContent("It's in the game.");
             post1.setAccount(account1);

             postService.createPost(post1);
         }
     }
}
