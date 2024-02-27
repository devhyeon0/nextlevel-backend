package com.nextlevel.post.repository;

import com.nextlevel.post.entity.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
}
