package com.nextlevel.post.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "tags")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(nullable = false)
    private String tagName;

    @Column(nullable = false)
    private Integer tagCount;
}
