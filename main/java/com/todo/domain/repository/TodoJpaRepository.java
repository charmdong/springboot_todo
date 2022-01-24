package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    Todo findBySeq(Long seq);

    List<Todo> findByMember(Member member);

    Long deleteBySeq(Long seq);

    Page<Todo> findByMember(Member member, Pageable pageable);

}
