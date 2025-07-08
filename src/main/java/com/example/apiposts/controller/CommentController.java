package com.example.apiposts.controller;

import com.example.apiposts.DTOs.CommentDTO;
import com.example.apiposts.DTOs.PostWithCommentDTO;
import com.example.apiposts.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{postId}/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping()
    public ResponseEntity<PostWithCommentDTO> saveCommentToPost(@PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        PostWithCommentDTO postWithCommentDTO = commentService.save(postId, commentDTO);
        return new ResponseEntity<>(postWithCommentDTO, HttpStatus.CREATED);
    }
}
