package com.todo.domain.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    JPAQueryFactory factory;

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

    @Test
    @DisplayName( "querydsl Test" )
    public void querydslTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("test");

        Member member = Member.createMember(memberRequest);
        em.persist(member);

        // when
        QMember m = QMember.member;

        Member findMember = factory.select(m).from(m).fetchOne();

        // then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember.getId()).isEqualTo("member1");
    }
}