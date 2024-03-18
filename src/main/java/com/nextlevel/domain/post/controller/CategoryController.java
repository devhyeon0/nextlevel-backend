package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.CategoryRequestDto;
import com.nextlevel.domain.post.dto.response.CategoryResponseDto;
import com.nextlevel.domain.post.service.CategoryService;
import com.nextlevel.global.dto.MultiResponseDto;
import com.nextlevel.global.dto.SingleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Objects> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.createCategory(categoryRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SingleResponseDto> patchCategory(@PathVariable("id") Long categoryId,
                                                           @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryRequestDto);

        return ResponseEntity.ok(new SingleResponseDto<>(categoryResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponseDto> getCategory(@PathVariable("id") Long categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(categoryId);

        return ResponseEntity.ok(new SingleResponseDto<>(categoryResponseDto));
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();

        return ResponseEntity.ok(new MultiResponseDto<>(categoryResponseDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
