package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    List<CommentReport> findByComment(Comment comment);
}
