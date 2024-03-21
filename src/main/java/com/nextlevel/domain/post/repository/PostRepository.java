package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"category", "user"})
    Optional<Post> findById(Long postId);
}
