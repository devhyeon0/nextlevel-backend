package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}
