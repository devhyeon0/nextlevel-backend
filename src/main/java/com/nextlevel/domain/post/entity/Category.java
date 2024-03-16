package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.post.dto.request.CategoryRequestDto;
import com.nextlevel.global.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    public void mappingPost(Post post) {
        posts.add(post);
    }

    public void update(CategoryRequestDto categoryRequestDto) {
        Optional.ofNullable(categoryRequestDto.getName()).ifPresent(value -> this.name = value);
        Optional.of(categoryRequestDto.isEnabled()).ifPresent(value -> this.enabled = value);
    }
}
