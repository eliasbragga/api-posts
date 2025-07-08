package com.example.apiposts.DTOs;

import com.example.apiposts.entity.Post;

import java.util.List;

public record PostWithCommentDTO(Long id, String title, String content, String author, List<CommentDTO> comments) {
    public static PostWithCommentDTO from(Post post, List<CommentDTO> comments) {
        return new PostWithCommentDTO(post.getId(),post.getTitle(), post.getUser().getName(), post.getContent(), comments);
    }
}
