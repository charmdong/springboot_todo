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

    public TodoRequest () {}

    public TodoRequest (String content, LocalDateTime expireDate, Member member) {
        this.content = content;
        this.expireDate = expireDate;
        this.member = member;
    }
}
