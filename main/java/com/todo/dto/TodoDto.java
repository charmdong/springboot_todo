package com.todo.dto;

import com.todo.domain.entity.Todo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDto {

    private Long seq;
    private String content;
    private LocalDateTime expireDate;
    private Boolean completeYn;

    public TodoDto () {}

    public TodoDto (Todo todo) {

        seq = todo.getSeq();
        content = todo.getContent();
        expireDate = todo.getExpireDate();
        completeYn = todo.getCompleteYn();
    }
}
