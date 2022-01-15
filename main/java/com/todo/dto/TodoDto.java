package com.todo.dto;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Todo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TodoDto {

    private Long seq;
    private String content;
    private LocalDateTime expireDate;
    private Boolean completeYn;
    private List<Comment> commentList;

    public TodoDto () {}

    public TodoDto (Todo todo) {

        seq = todo.getSeq();
        content = todo.getContent();
        expireDate = todo.getExpireDate();
        completeYn = todo.getCompleteYn();
        commentList = todo.getCommentList();
    }
}
