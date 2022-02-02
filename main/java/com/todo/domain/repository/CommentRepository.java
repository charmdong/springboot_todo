package com.todo.domain.repository;

import com.todo.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findBySeq(Long seq);

    Long deleteBySeq(Long seq);
}
