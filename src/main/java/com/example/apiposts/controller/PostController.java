package com.example.apiposts.controller;
import com.example.apiposts.DTOs.PostDTO;
import com.example.apiposts.entity.Post;
import com.example.apiposts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> dtos = postService.findAll().stream()
                .map(post -> new PostDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getLikes(),
                        post.getUser().getName()
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody PostDTO postDTO) {
        postService.save(postDTO);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Post> savePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        Post postSalvo = postService.updateLikesById(id, postDTO.likes());
        return ResponseEntity.ok(postSalvo);
    }

}
