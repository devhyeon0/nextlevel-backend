package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReport;
import com.nextlevel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {

    List<PostReport> findByPost(Post post);

    Optional<PostReport> findByUserAndPost(User user, Post post);
}
