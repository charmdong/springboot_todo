package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.dto.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberJpaRepository repository;

    @Test
    @DisplayName( "Find member by nickname using Spring data jpa" )
    public void findByNicknameWithJpaTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        // when
        Member member = Member.createMember( memberRequest );
        repository.save( member );
        List<Member> memberList = repository.findByNickname("hello");

        // then
        assertThat(memberList.size()).isEqualTo( 1 );
    }

    private Member createMember(String id) {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId(id);
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        Member member = Member.createMember( memberRequest );
        repository.save( member );
        System.out.println( "member = " + member );
        return repository.findById(id).get();
    }

    @Test
    @DisplayName( "Member 수정 테스트" )
    public void memberUpdateTest() throws Exception {
        // given
        Member member = createMember("member1");
        System.out.println( "member = " + member );
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setNickname( "world" );

        // when
        member.changeMemberInfo( memberRequest );
        Member update = repository.findById( "member1" ).get();
        System.out.println( "update = " + update );
        // then
        assertThat(update.getNickname()).isEqualTo( "world" );
    }

}