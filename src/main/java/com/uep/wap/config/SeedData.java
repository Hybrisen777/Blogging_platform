package com.uep.wap.config;

import com.uep.wap.model.Account;
import com.uep.wap.model.Authority;
import com.uep.wap.model.Comment;
import com.uep.wap.model.Post;
import com.uep.wap.repository.AuthorityRepository;
import com.uep.wap.service.AccountService;
import com.uep.wap.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.uep.wap.service.PostService;

import java.util.ArrayList;
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
     private CommentService commentService;

     @Autowired
     private AuthorityRepository authorityRepository;

     @Override
    public void run(String... args) throws Exception {
         List<Post> posts = postService.getAllPosts();

         if (posts.size() == 0) {

             Authority user = new Authority();
             user.setName("ROLE_USER");
             authorityRepository.save(user);

             //tworzenie rol do postow
//             Authority postCreator1 = new Authority();
//             postCreator1.setName("user");
//             authorityRepository.save(postCreator1);

             Authority admin = new Authority();
             admin.setName("ROLE_ADMIN");
             authorityRepository.save(admin);

             //tworzenie rol do postow
//             Authority postCreator2 = new Authority();
//             postCreator2.setName("admin");
//             authorityRepository.save(postCreator2);

             Account account1 = new Account();
             account1.setUsername("user");
             account1.setPassword("password");
             Set<Authority> authorities1 = new HashSet<>();
             authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
             //authorityRepository.findById("user").ifPresent(authorities1::add);
             account1.setAuthorities(authorities1);
             accountService.save(account1);

             Account account2 = new Account();
             account2.setUsername("admin");
             account2.setPassword("admin");
             Set<Authority> authorities2 = new HashSet<>();
             authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
             authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
             //authorityRepository.findById("admin").ifPresent(authorities2::add);
             account2.setAuthorities(authorities2);
             accountService.save(account2);

             Post post1 = new Post();
             post1.setTitle("Project goals and functionalities");
             post1.setTag("Passing terms, WAP");
             post1.setContent("Our web application presents functionalities of a blogging (microblogging) platform. CRUD operations are possible, logged users can create posts, posts are accessed via the main page, author of the post can edit it, users with admin role can delete posts.");
             post1.setAccount(account1);


             postService.createPost(post1);

             //dodawanie komentarzy
             Comment comment1 = new Comment();
             comment1.setText("testowy komentarz");
             comment1.setAccount(account2);
             comment1.setPost(post1);
             commentService.save(comment1);


             Post post2 = new Post();
             post2.setTitle("Project authors");
             post2.setTag("WAP, authors");
             post2.setContent("Jeremi Ranosz & Jan Stachowiak");
             post2.setAccount(account2);

             postService.createPost(post2);
         }
     }
}
