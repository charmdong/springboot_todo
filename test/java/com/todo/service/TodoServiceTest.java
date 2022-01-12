package com.todo.service;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.dto.CommentRequest;
import com.todo.dto.MemberRequest;
import com.todo.dto.TodoRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    TodoService todoService;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Todo 생성 테스트 by TodoService")
    public void createTodoTest() throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        Member member = getMember();
        todoRequest.setMember(member);

        // when
        Long seq = todoService.insert("member1", todoRequest);
        Todo todo = todoService.findOne(seq);

        // then
        assertThat(todo.getMember().getId()).isEqualTo("member1");
        assertThat(member.getTodoList().size()).isEqualTo(1);
    }

    private Member getMember() {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId("member1");
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        String id = memberService.insert(memberRequest);
        return memberService.findOne(id);
    }

    private Todo getTodo() {

        Member member = getMember();

        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(member);

        return todoService.findOne(todoService.insert(member.getId(), todoRequest));
    }

    private Comment getComment() {

        Todo todo = getTodo();

        CommentRequest request = new CommentRequest();
        request.setContent( "this is a comment" );
        request.setTodo( todo );

        return commentService.findOne( commentService.insert( todo.getSeq(), request ) );
    }

    @Test
    @DisplayName("Todo 수정 테스트")
    public void updateTodoTest() throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        Member member = getMember();
        todoRequest.setMember(member);

        Long seq = todoService.insert("member1", todoRequest);
        Todo todo = todoService.findOne(seq);

        // when
        TodoRequest todoRequest1 = new TodoRequest();

        todoRequest1.setContent("stop the world");
        Todo update = todoService.update( todo.getSeq(), todoRequest1 );

        List<Todo> todoList = member.getTodoList();
        Todo todo1 = todoList.get( 0 );

        // then
        assertThat(todoList.size()).isEqualTo(1);
        assertThat(todo1.getContent()).isEqualTo("stop the world");
    }

    @Test
    @DisplayName("Comment 생성 테스트")
    public void createCommentTest() throws Exception {
        // given
        Todo todo = getTodo();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContent("this is a comment");
        commentRequest.setTodo(todo);

        // when

        Long commentSeq = commentService.insert(todo.getSeq(), commentRequest);
        Comment comment = commentService.findOne(commentSeq);
        Todo findTodo = todoService.findOne(todo.getSeq());

        // then
        assertThat(comment.getContent()).isEqualTo("this is a comment");
        assertThat(todo.getCommentList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName( "Comment 수정 테스트" )
    public void updateCommentTest() throws Exception {
        // given
        Comment comment = getComment();

        CommentRequest request = new CommentRequest();
        request.setContent( "test" );

        // when
        Todo todo = comment.getTodo();
        commentService.update( comment.getSeq(), request );

        List<Comment> commentList = todo.getCommentList();
        Comment findComment = commentList.get( 0 );

        // then
        assertThat( findComment ).isSameAs( comment );
        assertThat( findComment.getContent() ).isEqualTo( comment.getContent() );
    }
}