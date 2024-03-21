package com.nextlevel.domain.comment.repository;

import com.nextlevel.domain.comment.entity.Comment;
import com.nextlevel.domain.comment.entity.CommentReport;
import com.nextlevel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    List<CommentReport> findByComment(Comment comment);

    Optional<CommentReport> findByUserAndComment(User user, Comment comment);
}
