package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.response.CategoryResponseDto;
import com.nextlevel.domain.post.dto.request.CategoryRequestDto;
import com.nextlevel.domain.post.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto categoryToCategoryResponseDto(Category category);
    List<CategoryResponseDto> categoriesToCategoryResponseDtos(List<Category> categories);
}
