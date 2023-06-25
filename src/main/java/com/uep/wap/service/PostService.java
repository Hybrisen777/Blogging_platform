package com.uep.wap.service;

import com.uep.wap.model.Comment;
import com.uep.wap.model.Post;
import com.uep.wap.repository.CommentRepository;
import com.uep.wap.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        return postRepository.save(post);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void delete(Post post){
        //usuwanie wszystkich komentazry z posta
        getComments(post.getId()).forEach(comment -> commentRepository.delete(comment));
        //usuwanie posta
        postRepository.delete(post);
    }

}

