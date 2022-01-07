package com.todo.domain.entity;

import com.todo.dto.CommentRequest;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Getter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_seq")
    private Long seq;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;

    // Constructor Method
    public Comment createComment(CommentRequest request) {

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
