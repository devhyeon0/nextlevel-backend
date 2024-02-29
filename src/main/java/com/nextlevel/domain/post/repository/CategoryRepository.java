package com.nextlevel.domain.post.repository;

import com.nextlevel.domain.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
