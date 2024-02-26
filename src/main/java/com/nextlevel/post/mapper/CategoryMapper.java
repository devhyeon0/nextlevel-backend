package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.CategoryRequestDto;
import com.nextlevel.post.dto.CategoryResponseDto;
import com.nextlevel.post.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);

    @Mapping(source = "id", target = "categoryId")
    CategoryResponseDto categoryToCategoryResponseDto(Category category);
}
