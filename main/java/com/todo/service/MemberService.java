package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.repository.MemberRepository;
import com.todo.dto.MemberDto;
import com.todo.dto.MemberRequest;
import com.todo.exception.member.DeleteMemberException;
import com.todo.exception.member.InsertMemberException;
import com.todo.exception.member.MemberNotFoundException;
import com.todo.exception.member.UpdateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String insert (MemberRequest request) {

        Member member = Member.createMember(request);

        try{
            memberRepository.save(member);
        }
        catch (IllegalArgumentException ie) {
            throw new InsertMemberException(ie);
        }

        return member.getId();
    }

    @Transactional(readOnly = true)
    public MemberDto findOne (String id) {

        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new MemberNotFoundException("사용자 정보가 존재하지 않습니다."));

        return new MemberDto(member);
    }

    public MemberDto update (String id, MemberRequest request) {

        Member member = memberRepository
                        .findById(id)
                        .orElseThrow(() -> new UpdateMemberException("사용자 정보가 존재하지 않습니다."));

        member.changeMemberInfo(request);

        return new MemberDto(member);
    }

    public void delete (String id) {

        try {
            memberRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new DeleteMemberException("사용자 정보가 존재하지 않습니다.");
        }
    }
}
