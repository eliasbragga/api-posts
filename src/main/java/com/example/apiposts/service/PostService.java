package com.example.apiposts.service;


import com.example.apiposts.DTOs.PostDTO;
import com.example.apiposts.entity.Post;
import com.example.apiposts.entity.User;
import com.example.apiposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Post save(PostDTO postDTO) {
        User user;

        if (userService.existsByName(postDTO.author())) {
            user = userService.findByName(postDTO.author());
        } else {
            user = userService.save(
                    User.builder().name(postDTO.author()).build()
            );
        }

        Post post = Post.builder()
                .title(postDTO.title())
                .content(postDTO.content())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    public Post updateLikesById(Long id, Integer likes) {
        Post postToUpdate = findById(id);
        postToUpdate.setLikes(likes);
        return postRepository.save(postToUpdate);
    }


    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
