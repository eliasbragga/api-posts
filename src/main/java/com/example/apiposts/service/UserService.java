package com.example.apiposts.service;

import com.example.apiposts.entity.User;
import com.example.apiposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String author) {
        return userRepository.findByName(author);
    }

    public boolean existsByName(String author) {
        return userRepository.existsByName(author);
    }
}
