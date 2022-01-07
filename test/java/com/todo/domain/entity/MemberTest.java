package com.todo.domain.entity;

import com.todo.dto.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("Member 생성 테스트")
    public void createMemberTest () throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("test");

        // when
        Member member = Member.createMember(memberRequest);

        // then
        assertThat(member.getId()).isEqualTo("member1");
    }

}