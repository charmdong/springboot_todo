package com.todo.dto;

import com.todo.domain.entity.Member;
import lombok.Data;

@Data
public class MemberDto {

    private String id;
    private String nickname;

    public MemberDto() {}

    public MemberDto(Member member) {
        id = member.getId();
        nickname = member.getNickname();
    }
}
