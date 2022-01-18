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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    MemberRepository repository;

    @PersistenceContext
    EntityManager em;

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
//        em.persist( member ); // 영속성 컨텍스트 1차 캐시에 등록
//        System.out.println( "member = " + member );

        memberJpaRepository.save( member );
        System.out.println( "member = " + member );

        Member find = memberJpaRepository.findById( "member1" ).get(); // 영속성 컨텍스트 1차 캐시에서 가져옴
        System.out.println( "find = " + find );
    }
}