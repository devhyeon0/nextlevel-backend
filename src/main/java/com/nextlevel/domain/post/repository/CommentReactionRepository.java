package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    List<CommentReaction> findByComment(Comment comment);
}
