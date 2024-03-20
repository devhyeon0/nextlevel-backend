package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.audit.BaseEntity;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.EnumType.*;

@Getter
@Entity
@Table(name = "posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int views;

    @Enumerated(STRING)
    @Column(nullable = false)
    private PostStatus status;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int reportCount;

    public void addViewCount() {
        this.views += 1;
    }

    public void addReportCount() {
        this.reportCount += 1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostReaction> postReactions = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostReport> postReports = new ArrayList<>();

    public void mappingUser(User user) {
        this.user = user;
        user.mappingPost(this);
    }

    public void mappingCategory(Category category) {
        this.category = category;
        category.mappingPost(this);
    }

    public void mappingComment(Comment comment) {
        comments.add(comment);
    }

    public void mappingPostReaction(PostReaction postReaction) {
        postReactions.add(postReaction);
    }

    public void mappingPostReport(PostReport postReport) {
        postReports.add(postReport);
    }

    public void update(PostRequestDto postDto) {
        Optional.ofNullable(postDto.getTitle()).ifPresent(value -> this.title = value);
        Optional.ofNullable(postDto.getContent()).ifPresent(value -> this.content = value);
        Optional.ofNullable(postDto.getStatus()).ifPresent(value -> this.status = value);
    }
}
