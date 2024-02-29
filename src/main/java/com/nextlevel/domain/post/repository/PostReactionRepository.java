package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
}
