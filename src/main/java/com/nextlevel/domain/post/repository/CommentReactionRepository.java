package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
}
