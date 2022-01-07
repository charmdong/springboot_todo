package com.todo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequest {

    private String id;
    private String password;
    private String nickname;
}
