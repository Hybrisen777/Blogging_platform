//package com.uep.wap.controller;
//
//
//import com.uep.wap.dto.BlogDTO;
//import com.uep.wap.model.Blog;
//import com.uep.wap.service.BlogService;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "/api")
//public class BlogController {
//
//    private final BlogService blogService;
//
//
//    public BlogController(BlogService blogService) {
//        this.blogService = blogService;
//    }
//
////    @GetMapping(path = "/blog/{id}")
////    public String showBlog(@PathVariable Long id, Model model){
////        Optional<Blog> optionalPost = blogService.getBlogById(id);
////        Blog post = optionalPost.get();
////        model.addAttribute("post", post);
////        return "blog";
////    }
//
////    @PostMapping(path = "/blogs")
////    public String addBlog(@RequestBody BlogDTO blogDTO){
////        blogService.addBlog(blogDTO);
////        return "Blogs added!";
////    }
//
//
//
//    @PostMapping("/add")
//    public String createNewBlog(@ModelAttribute Blog blog) {
//
//        BlogService.save(blog);
//        return "redirect:/api/blog/" + blog.getId();
//    }
//
//}
//
//
