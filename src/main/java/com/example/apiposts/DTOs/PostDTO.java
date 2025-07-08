package com.example.apiposts.DTOs;

import com.example.apiposts.entity.Post;

public record PostDTO(Long id, String title, String content, Integer likes, long commentCount, String author) {

     public static PostDTO from(Post post, long commentCount) {
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getLikes(), commentCount, post.getUser().getName());
    }
}
