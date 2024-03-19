package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {

    List<PostReport> findByPost(Post post);
}
