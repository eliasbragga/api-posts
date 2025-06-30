package com.example.apiposts.DTOs;

public record PostDTO(Long id,  String title, String content, Integer likes, String author) {
}
