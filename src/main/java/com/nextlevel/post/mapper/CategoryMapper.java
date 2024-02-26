package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.CategoryRequestDto;
import com.nextlevel.post.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);
}
