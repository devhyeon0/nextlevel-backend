package com.nextlevel.post.controller;

import com.nextlevel.post.dto.request.CategoryRequestDto;
import com.nextlevel.post.dto.response.CategoryResponseDto;
import com.nextlevel.post.service.CategoryService;
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
    public ResponseEntity<CategoryResponseDto> patchCategory(@PathVariable("id") Long categoryId,
                                                             @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryRequestDto);

        return ResponseEntity.ok(categoryResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable("id") Long categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(categoryId);

        return ResponseEntity.ok(categoryResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();

        return ResponseEntity.ok(categoryResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
