package com.uep.wap.controller;

import com.uep.wap.model.Account;
import com.uep.wap.model.Post;
import com.uep.wap.service.AccountService;
import com.uep.wap.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        //szukanie posta po id
        Optional<Post> optionalPost = postService.getPostById(id);
        //jeśli post istnieje, dodaj go do modelu
        if (optionalPost.isPresent()) {
        Post post = optionalPost.get();
        model.addAttribute("post", post);
        return "post";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/create")
    public String createNewPost(Model model) {
        //konto user podpięte jako obecny autor wszystkich tworzonych postów
        Optional<Account> optionalAccount = accountService.findByUsername("user");
        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post",post);
            return "post_create";
        } else {
            //do zrobienia- strona obsługująca błędy
            return "404";
        }
    }

    @PostMapping("/posts/create")
    public String saveNewPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

}
