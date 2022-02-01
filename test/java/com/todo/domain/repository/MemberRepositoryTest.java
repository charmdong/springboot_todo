package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Member 생성 테스트")
    public void insertMemberTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        Member member = Member.createMember(memberRequest);

        // when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(findMember).isEqualTo(member);
    }

}