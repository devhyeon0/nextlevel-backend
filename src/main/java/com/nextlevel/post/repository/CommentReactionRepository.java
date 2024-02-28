package com.nextlevel.post.repository;

import com.nextlevel.post.entity.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
}
