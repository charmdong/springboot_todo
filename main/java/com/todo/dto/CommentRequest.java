package com.todo.dto;

import com.todo.domain.entity.Todo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentRequest {

    private Todo todo;
    private String content;
}
