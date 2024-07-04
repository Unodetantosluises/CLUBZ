package com.rungroup.web.repository;

import com.rungroup.web.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String url);
    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);
}
