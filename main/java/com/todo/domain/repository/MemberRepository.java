package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void insert (Member member) {
        em.persist(member);
    }

    public Member findOne (String id) {
        return em.find(Member.class, id);
    }

    public void delete (String id) {
        Member member = findOne(id);
        em.remove(member);
    }
}
