package com.uep.wap.service;

import com.uep.wap.model.Comment;
import com.uep.wap.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostId(postId);
    }


    public void save(Comment comment) {
        if (comment.getId() == null) {
            comment.setCreatedAt(LocalDateTime.now());
        }
        commentRepository.save(comment);
    }

    //TODO dodac usuwanie komentarzy
//    public Optional<Comment> getCommentById(Long id) {
//        return commentRepository.findById(id);
//    }
//    public boolean deleteComment(Comment comment) {
//        commentRepository.delete(comment);
//        return true;
//    }
}

