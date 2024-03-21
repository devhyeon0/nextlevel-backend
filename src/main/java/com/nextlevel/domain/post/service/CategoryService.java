package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.response.CategoryResponseDto;
import com.nextlevel.domain.post.mapper.CategoryMapper;
import com.nextlevel.domain.post.repository.CategoryRepository;
import com.nextlevel.domain.post.dto.request.CategoryRequestDto;
import com.nextlevel.domain.post.entity.Category;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
