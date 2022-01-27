package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.MemberRepository;
import com.todo.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TodoService todoService;

    @Autowired
    CommentService commentService;


}