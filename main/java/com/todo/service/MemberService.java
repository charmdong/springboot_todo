package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.repository.MemberJpaRepository;
import com.todo.dto.MemberDto;
import com.todo.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberRepository;

    public String insert (MemberRequest request) {

        Member member = Member.createMember( request );
        memberRepository.save( member );

        return member.getId();
    }

    @Transactional(readOnly = true)
    public MemberDto findOne (String id) {

        return new MemberDto( memberRepository.getById( id ) );
    }

    public void delete (String id) {

        memberRepository.deleteById( id );
    }
}
