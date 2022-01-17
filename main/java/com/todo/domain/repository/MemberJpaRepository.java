package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, String> {

    List<Member> findByNickname(String nickname);

}
