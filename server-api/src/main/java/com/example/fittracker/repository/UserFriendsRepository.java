package com.example.fittracker.repository;

import com.example.fittracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepository extends JpaRepository<User, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO user_friends (user_id, friend_id) VALUES (:userId, :friendId)", nativeQuery = true)
//    void saveUserFriendRelationship(Long userId, Long friendId);
}
