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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoJpaRepositoryTest {

    @Autowired
    TodoJpaRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Todo 생성 테스트")
    public void createTodoTest () throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent( "hello world" );
        todoRequest.setExpireDate( LocalDateTime.now() );
        todoRequest.setMember( createMember() );

        Todo todo = Todo.createTodo( todoRequest );

        // when
        repository.save( todo );

        // then
        assertThat( todo.getSeq() ).isEqualTo( 1 );
    }

    private Member createMember () {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId( "member1" );
        memberRequest.setPassword( "1234" );
        memberRequest.setNickname( "hello" );

        Member member = Member.createMember( memberRequest );

        memberRepository.insert( member );

        return member;
    }

    private Todo createTodo () {

        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent( "hello world" );
        todoRequest.setExpireDate( LocalDateTime.now() );
        todoRequest.setMember( createMember() );

        Todo todo = Todo.createTodo( todoRequest );

        return repository.save( todo );
    }

    @Test
    @DisplayName("Seq로 Todo 조회하기")
    public void findBySeqTest () throws Exception {
        // given
        Todo todo = createTodo();

        // when
        Todo find = repository.findBySeq( todo.getSeq() );

        // then
        assertThat( find.getContent() ).isEqualTo( "hello world" );
    }

    @Test
    @DisplayName("Member로 TODO 목록 조회하기")
    public void findByMemberTest () throws Exception {
        // given
        Todo todo = createTodo();
        Member member = todo.getMember();

        // when
        List<Todo> todoList = repository.findByMember( member );

        // then
        assertThat( todoList.size() ).isEqualTo( 1 );
        assertThat( todoList.get( 0 ).getContent() ).isEqualTo( "hello world" );
    }

    @Test
    @DisplayName("delete Test")
    public void deleteTodoTest () throws Exception {
        // given
        Todo todo = createTodo();

        // when
        int count = em.createQuery( "delete from Todo t where t.seq =: seq" ).setParameter( "seq", todo.getSeq() ).executeUpdate();
        System.out.println( "count = " + count );

        int count2 = em.createQuery( "delete from Todo t where t.seq =: seq" ).setParameter( "seq", todo.getSeq() ).executeUpdate();
        System.out.println( "count2 = " + count2 );
    }

}