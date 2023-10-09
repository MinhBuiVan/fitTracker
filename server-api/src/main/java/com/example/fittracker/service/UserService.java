package com.example.fittracker.service;

import com.example.fittracker.exception.ResourceNotFoundException;
import com.example.fittracker.model.User;

import com.example.fittracker.repository.UserFriendsRepository;
import com.example.fittracker.repository.UserRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFriendsRepository userFriendsRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setGender(userDetails.getGender());
        user.setBirthday(userDetails.getBirthday());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());
        user.setWeight(userDetails.getWeight());
        user.setHeight(userDetails.getHeight());
        user.setImage(userDetails.getImage());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }


    public List<User> searchFriends(String query) {
        return userRepository.findByUsernameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(query, query, query);
    }
    @Transactional
    @Cacheable("sendFriendRequest")
    public boolean sendFriendRequest(Long userId, Long friendId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> friendOptional = userRepository.findById(friendId);

        if (userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();

            user.getFriends().add(friend);
            userRepository.save(user);

            return true;
        } else {
            return false;
        }
    }
//    @Transactional
//    public void confirmFriendRequest(Long userId, Long friendId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//        User friend = userRepository.findById(friendId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid friend ID"));
//
//        if (user.getFriends().contains(friend) && friend.getFriends().contains(user)) {
//            user.getFriends().remove(friend);
//            // Additional logic or actions for confirming friend request
//        } else {
//            throw new IllegalArgumentException("Friend request does not exist");
//        }
//    }

}
