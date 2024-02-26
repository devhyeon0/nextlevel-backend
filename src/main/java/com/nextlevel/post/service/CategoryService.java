package com.nextlevel.post.service;

import com.nextlevel.post.dto.CategoryRequestDto;
import com.nextlevel.post.dto.CategoryResponseDto;
import com.nextlevel.post.entity.Category;
import com.nextlevel.post.mapper.CategoryMapper;
import com.nextlevel.post.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public CategoryResponseDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        return mapper.categoryToCategoryResponseDto(category);
    }

    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return mapper.categoriesToCategoryResponseDtos(categories);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
