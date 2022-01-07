package com.todo.dto;

import com.todo.domain.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TodoRequest {

    private String content;
    private LocalDateTime expireDate;
    private Member member;
}
