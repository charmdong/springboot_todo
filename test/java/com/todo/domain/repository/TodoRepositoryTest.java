package com.todo.domain.repository;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.dto.MemberRequest;
import com.todo.dto.TodoRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Todo 생성 테스트 by TodoRepository")
    public void createTodoTest() throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(createMember());

        Todo todo = Todo.createTodo(todoRequest);

        // when
        todoRepository.insert(todo);
        List<Todo> todos = todoRepository.findTodos(todo.getMember());

        // then
        assertThat(todos.size()).isEqualTo(1);
    }

    private Member createMember() {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        Member member = Member.createMember(memberRequest);

        memberRepository.insert(member);

        return member;
    }

}