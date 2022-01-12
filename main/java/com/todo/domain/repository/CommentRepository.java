package com.todo.domain.repository;

import com.todo.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void insert(Comment comment) {

        em.persist(comment);
    }

    public Comment findOne(Long seq) {

        return em.find(Comment.class, seq);
    }

    public void delete(Long seq) {

        Comment comment = em.find(Comment.class, seq);
        em.remove(comment);
    }


}
