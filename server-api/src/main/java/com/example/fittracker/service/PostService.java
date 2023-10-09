package com.example.fittracker.service;

import com.example.fittracker.model.Activity;
import com.example.fittracker.model.Post;
import com.example.fittracker.model.User;
import com.example.fittracker.payload.request.CreatePostRequest;
import com.example.fittracker.repository.PostRepository;
import com.example.fittracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    public Post createPost(String content, String imageURl, LocalDateTime create, Long userId) {
            Post posts = new Post();
            posts.setContent(content);
            posts.setImage(imageURl);
            posts.setCreateDate(create);

            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            posts.setUser(user);

            return postRepository.save(posts);

    }
}
