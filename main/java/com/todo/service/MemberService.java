package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.repository.MemberRepository;
import com.todo.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String insert(MemberRequest request) {

        Member member = Member.createMember(request);
        memberRepository.insert(member);

        return member.getId();
    }

    @Transactional(readOnly = true)
    public Member findOne(String id) {

        return memberRepository.findOne(id);
    }

    public void delete(String id) {

        memberRepository.delete(id);
    }
}
