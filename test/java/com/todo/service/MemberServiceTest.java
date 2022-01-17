package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.repository.MemberJpaRepository;
import com.todo.domain.repository.MemberRepository;
import com.todo.dto.MemberDto;
import com.todo.dto.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberJpaRepository repository;

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
        MemberDto findMember = memberService.findOne(id);

        // then
        assertThat(findMember.getId()).isEqualTo("member1");
    }

    @Test
    public void persistenceContextTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        Member member = Member.createMember( memberRequest );

        // when

        // then
    }
}