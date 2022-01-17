package com.todo.dto;

import com.todo.domain.entity.Member;
import lombok.Data;

@Data
public class MemberDto {

    private String id;
    private String nickname;

    public MemberDto() {}

    public MemberDto (String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public MemberDto(Member member) {
        id = member.getId();
        nickname = member.getNickname();
    }
}
