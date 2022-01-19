package com.todo.domain.repository;

import com.todo.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Comment findBySeq(Long seq);

    Long deleteBySeq(Long seq);
}
