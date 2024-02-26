package com.nextlevel.post.service;

import com.nextlevel.post.dto.CategoryRequestDto;
import com.nextlevel.post.dto.CategoryResponseDto;
import com.nextlevel.post.entity.Category;
import com.nextlevel.post.mapper.CategoryMapper;
import com.nextlevel.post.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public void createCategory(CategoryRequestDto categoryRequestDto) {
        categoryRepository.save(mapper.categoryRequestDtoToCategory(categoryRequestDto));
    }

    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        category.update(categoryRequestDto);

        return mapper.categoryToCategoryResponseDto(category);
    }
}
