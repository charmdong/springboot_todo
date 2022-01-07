package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public void insert(Todo todo) {

        em.persist(todo);
    }

    public List<Todo> findTodos(Member member) {

        return em.createQuery("select t from Todo t where t.member =: member", Todo.class)
                .setParameter("member", member)
                .getResultList();
    }

    public Todo findOne(Long seq) {

        return em.find(Todo.class, seq);
    }

    public void delete(Long seq) {

        em.remove(findOne(seq));
    }
}
