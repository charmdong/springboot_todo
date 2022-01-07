package com.todo.domain.entity;

import com.todo.dto.CommentRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_seq")
    private Long seq;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_seq")
    private Todo todo;

    // Constructor Method
    public static Comment createComment(CommentRequest request) {

        Comment comment = new Comment();

        comment.content = request.getContent();

        return comment;
    }

    // 연관 관계 메서드
    public void changeCommentInfo(CommentRequest request) {

        if(StringUtils.hasText(request.getContent())) {
            this.content = request.getContent();
        }
    }
}
