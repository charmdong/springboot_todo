package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findBySeq(Long seq);

    Long deleteBySeq(Long seq);

    Page<Todo> findByMember(Member member, Pageable pageable);

}
