package com.todo.service;

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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("Todo 생성 테스트 by TodoService")
    public void createTodoTest() throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(insertMember());

        // when
        Long seq = todoService.insert(todoRequest);
        Todo todo = todoService.findOne(seq);

        // then
        assertThat(todo.getMember().getId()).isEqualTo("member1");
    }

    private Member insertMember() {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        String id = memberService.insert(memberRequest);
        return memberService.findOne(id);
    }
}