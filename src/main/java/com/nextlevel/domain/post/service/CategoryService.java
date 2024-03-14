package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.response.CategoryResponseDto;
import com.nextlevel.domain.post.mapper.CategoryMapper;
import com.nextlevel.domain.post.repository.CategoryRepository;
import com.nextlevel.domain.post.dto.request.CategoryRequestDto;
import com.nextlevel.domain.post.entity.Category;
import com.nextlevel.global.exception.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
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
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.CATEGORY_NOT_FOUND));

        category.update(categoryRequestDto);

        return mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.CATEGORY_NOT_FOUND));

        return mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return mapper.categoriesToCategoryResponseDtos(categories);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
