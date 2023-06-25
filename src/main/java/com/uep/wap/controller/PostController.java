package com.uep.wap.controller;

import com.uep.wap.model.Account;
import com.uep.wap.model.Comment;
import com.uep.wap.model.Post;
import com.uep.wap.service.AccountService;
import com.uep.wap.service.CommentService;
import com.uep.wap.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {
        //szukanie posta po id
        Optional<Post> optionalPost = postService.getPostById(id);
        //jeśli post istnieje, dodaj go do modelAttribute żeby był widoczny w html
        if (optionalPost.isPresent()) {
        Post post = optionalPost.get();
        model.addAttribute("post", post);
        //komentarze po id posta
        List<Comment> comments = commentService.getComments(id);
        model.addAttribute("comments", comments);

        //dodawanie nazwy uzytkownika wchodzacego w post do modelu
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        model.addAttribute("authUsername", authUsername);

        post.setViews(post.getViews() + 1);
        postService.save(post);
        
        return "post";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/create")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }


        Optional<Account> optionalAccount = accountService.findByUsername(authUsername);
        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post",post);
            return "post_create";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/posts/create")
    public String saveNewPost(@ModelAttribute Post post, Principal principal) {

        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/{id}/comment")
    public String saveComment(@ModelAttribute Post post, @ModelAttribute Comment comment) {
        System.out.println("zapisuje");
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        commentService.save(comment);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/comment")
    @PreAuthorize("isAuthenticated()")
    public String getCommentPost(@PathVariable Long id, Model model, Principal principal, @ModelAttribute Comment comment){
        //szukanie posta po id
        Optional<Post> optionalPost = postService.getPostById(id);

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findByUsername(authUsername);
        if (optionalAccount.isPresent() && optionalPost.isPresent()) {
            Post post = optionalPost.get();
            comment.setAccount(optionalAccount.get());
            comment.setPost(post);
            model.addAttribute("comment", comment);
        }

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            model.addAttribute("post", post);
            System.out.println("tu jestem");
            return "post_comment";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model, Authentication authentication){
        //szukanie posta po id
        Optional<Post> optionalPost = postService.getPostById(id);
        //jeśli post istnieje, dodaj go do modelu w html
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (!post.getAccount().getUsername().equals(authentication.getName())) {
                return "post_badAccess.html";
            }
            model.addAttribute("post", post);
            return "post_edit";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model, Authentication authentication) {

        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            if (!existingPost.getAccount().getUsername().equals(authentication.getName())) {
                return "post_badAccess.html";
            }
//            if (!existingPost.getAccount().getUsername().equals(authentication.getPrincipal())) {
//                return "post_badAccess.html";
//            }
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());

            postService.save(existingPost);
        }

        return "redirect:/posts/" + post.getId();
    }


    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        // find post by id
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            postService.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }

//    @GetMapping("/posts/{id}/deleteComment")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String deleteComment(@PathVariable Long id) {
//
//        // find post by id
//        Optional<Comment> optionalComment = commentService.getCommentById(id);
//        if (optionalComment.isPresent()) {
//            Comment comment = optionalComment.get();
//
//            commentService.deleteComment(comment);
//            return "redirect:/";
//        } else {
//            return "404";
//        }
//    }

    @GetMapping("/posts/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public String likePost(@PathVariable Long id) {

        // find post by id
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikes(post.getLikes() + 1);
            postService.save(post);
            return "redirect:/posts/" + post.getId();
        } else {
            return "404";
        }
    }


}
