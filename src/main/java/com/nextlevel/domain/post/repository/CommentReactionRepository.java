package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.CommentReaction;
import com.nextlevel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    List<CommentReaction> findByComment(Comment comment);

    Optional<CommentReaction> findByUserAndComment(User user, Comment comment);
}
