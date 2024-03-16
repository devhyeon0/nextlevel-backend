package com.nextlevel.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.CommentReaction;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReaction;
import com.nextlevel.global.audit.BaseTimeEntity;
import com.nextlevel.domain.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.EnumType.*;

@Getter
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    @Enumerated(STRING)
    @Column(nullable = false)
    private LoginProvider provider;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostReaction> postReactions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentReaction> commentReactions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public void mappingPost(Post post) {
        posts.add(post);
    }

    public void mappingPostReaction(PostReaction postReaction) {
        postReactions.add(postReaction);
    }

    public void mappingCommentReaction(CommentReaction commentReaction) {
        commentReactions.add(commentReaction);
    }

    public void mappingComment(Comment comment) {
        comments.add(comment);
    }

    public void update(UserRequestDto userDto) {
        Optional.ofNullable(userDto.getNickname()).ifPresent(value -> this.nickname = value);
        Optional.ofNullable(userDto.getPassword()).ifPresent(value -> this.password = value);
        Optional.ofNullable(userDto.getUserRole()).ifPresent(value -> this.userRole = value);
        Optional.ofNullable(userDto.getStatus()).ifPresent(value -> this.status = value);
    }
}
