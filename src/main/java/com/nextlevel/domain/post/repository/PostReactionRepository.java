package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

    List<PostReaction> findByPost(Post post);
}
