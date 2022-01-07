package com.todo.domain.entity;

import com.todo.dto.MemberRequest;
import com.todo.dto.TodoRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    @DisplayName("TODO 생성 테스트")
    public void createTodoTest() throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent("hello world!!!");
        todoRequest.setExpireDate(LocalDateTime.now());

        // when
        Todo todo = Todo.createTodo(todoRequest);

        // then
        assertThat(todo.getContent()).isEqualTo("hello world!!!");
    }

    @Test
    @DisplayName("TODO 생성 테스트 (사용자 정보 포함)")
    public void createTodoTestWithMember() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("test");

        Member member = Member.createMember(memberRequest);

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent("hello world!!!");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(member);

        // when
        Todo todo = Todo.createTodo(todoRequest);

        // then
        assertThat(todo.getMember()).isEqualTo(member);
    }
}