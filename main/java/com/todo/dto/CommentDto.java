package com.todo.dto;

import com.todo.domain.entity.Comment;
import lombok.Data;

@Data
public class CommentDto {

    private Long seq;
    private String content;

    public CommentDto () {}

    public CommentDto (Comment comment) {

        seq = comment.getSeq();
        content = comment.getContent();

    }
}
