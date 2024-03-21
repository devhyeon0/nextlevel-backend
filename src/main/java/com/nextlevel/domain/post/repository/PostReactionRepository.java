package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReaction;
import com.nextlevel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

    List<PostReaction> findByPost(Post post);

    Optional<PostReaction> findByUserAndPost(User user, Post post);
}
