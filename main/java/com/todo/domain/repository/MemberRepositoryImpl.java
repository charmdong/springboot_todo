package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public void join (Member member) {

        em.persist(member);
    }
}
