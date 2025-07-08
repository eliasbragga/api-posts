package com.example.apiposts.DTOs;

import com.example.apiposts.entity.Comment;

public record CommentDTO(Long id, String username, String content) {
    public static CommentDTO from(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getUsername(), comment.getContent());
    }
}
