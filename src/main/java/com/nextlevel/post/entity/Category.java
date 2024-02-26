package com.nextlevel.post.entity;

import com.nextlevel.common.audit.BaseTimeEntity;
import com.nextlevel.post.dto.request.CategoryRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Entity
@Builder
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;

    public void update(CategoryRequestDto categoryRequestDto) {
        Optional.ofNullable(categoryRequestDto.getName()).ifPresent(value -> this.name = value);
        Optional.of(categoryRequestDto.isEnabled()).ifPresent(value -> this.enabled = value);
    }
}
