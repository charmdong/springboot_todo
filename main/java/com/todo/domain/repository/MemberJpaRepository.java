package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, String> {

    Optional<Member> findById(String id);

    List<Member> findByNickname(String nickname);

}
