package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.dto.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("Member 생성 테스트")
    public void createMemberTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        // when
        String id = memberService.insert(memberRequest);
        Member findMember = memberService.findOne(id);

        // then
        assertThat(findMember.getId()).isEqualTo("member1");
    }

}