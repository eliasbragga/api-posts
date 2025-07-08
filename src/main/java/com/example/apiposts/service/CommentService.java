package com.example.apiposts.service;


import com.example.apiposts.DTOs.CommentDTO;
import com.example.apiposts.DTOs.PostWithCommentDTO;
import com.example.apiposts.entity.Comment;
import com.example.apiposts.entity.Post;
import com.example.apiposts.repository.CommentRepository;
import com.example.apiposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public long countByPostId(long postId) {
        return commentRepository.countByPostId(postId);
    }

    public List<Comment> findByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }

    public PostWithCommentDTO save(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).get();
        Comment comment = Comment.builder()
                .content(commentDTO.content())
                .username(returnUsernameEdited(commentDTO.username()))
                .post(post).build();
        commentRepository.save(comment);

        List<CommentDTO> comments = commentRepository.findByPostId(postId)
                .stream().map(CommentDTO::from).toList();
        return  PostWithCommentDTO.from(post, comments);
    }

    public String returnUsernameEdited(String author) {
        return Optional.ofNullable(author)
                .filter(username -> !username.isBlank())
                .orElse("@anonymous");
    }
}
