package com.uep.wap.config;

import com.uep.wap.model.Account;
import com.uep.wap.model.Authority;
import com.uep.wap.model.Post;
import com.uep.wap.repository.AuthorityRepository;
import com.uep.wap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.uep.wap.service.PostService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
     @Autowired
     private PostService postService;
     @Autowired
     private AccountService accountService;

     @Autowired
     private AuthorityRepository authorityRepository;

     @Override
    public void run(String... args) throws Exception {
         List<Post> posts = postService.getAllPosts();

         if (posts.size() == 0) {

             Authority user = new Authority();
             user.setName("ROLE_USER");
             authorityRepository.save(user);

             Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

             Account account1 = new Account();
             account1.setUsername("user");
             account1.setPassword("password");
             Set<Authority> authorities1 = new HashSet<>();
             authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
             accountService.save(account1);

             Account account2 = new Account();
             account2.setUsername("admin");
             account2.setPassword("admin");
             Set<Authority> authorities2 = new HashSet<>();
             authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
             authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
             accountService.save(account2);

             Post post1 = new Post();
             post1.setTitle("EA Sports");
             post1.setContent("It's in the game.");
             post1.setAccount(account1);

             postService.createPost(post1);

             Post post2 = new Post();
             post2.setTitle("Post admina");
             post2.setContent("UWAGA TEST");
             post2.setAccount(account2);

             postService.createPost(post2);
         }
     }
}
