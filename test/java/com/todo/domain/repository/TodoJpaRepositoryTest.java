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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    MemberJpaRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Todo 생성 테스트")
    public void createTodoTest () throws Exception {
        // given
        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(createMember("member1"));

        Todo todo = Todo.createTodo(todoRequest);

        // when
        repository.save(todo);

        // then
        assertThat(todo.getSeq()).isEqualTo(1);
    }

    private Member createMember (String memberId) {

        MemberRequest memberRequest = new MemberRequest();

        memberRequest.setId(memberId);
        memberRequest.setPassword("1234");
        memberRequest.setNickname("hello");

        Member member = Member.createMember(memberRequest);

        memberRepository.save(member);

        return memberRepository.findById(memberId).get();
    }

    private Todo createTodo (String memberId) {

        TodoRequest todoRequest = new TodoRequest();

        todoRequest.setContent("hello world");
        todoRequest.setExpireDate(LocalDateTime.now());
        todoRequest.setMember(createMember(memberId));

        Todo todo = Todo.createTodo(todoRequest);

        return repository.save(todo);
    }

    @Test
    @DisplayName("Seq로 Todo 조회하기")
    public void findBySeqTest () throws Exception {
        // given
        Todo todo = createTodo("member1");

        // when
        Todo find = repository.findBySeq(todo.getSeq());

        // then
        assertThat(find.getContent()).isEqualTo("hello world");
    }

    @Test
    @DisplayName("Member로 TODO 목록 조회하기")
    public void findByMemberTest () throws Exception {
        // given
        Todo todo = createTodo("member1");
        Member member = todo.getMember();

        // when
        List<Todo> todoList = repository.findByMember(member);

        // then
        assertThat(todoList.size()).isEqualTo(1);
        assertThat(todoList.get(0).getContent()).isEqualTo("hello world");
    }

    @Test
    @DisplayName("delete Test")
    public void deleteTodoTest () throws Exception {
        // given
        Todo todo = createTodo("member1");

        // when
        int count = em.createQuery("delete from Todo t where t.seq =: seq").setParameter("seq", todo.getSeq()).executeUpdate();
        System.out.println("count = " + count);

        int count2 = em.createQuery("delete from Todo t where t.seq =: seq").setParameter("seq", todo.getSeq()).executeUpdate();
        System.out.println("count2 = " + count2);
    }

    @Test
    @DisplayName("Paging Test")
    public void pagingTest () throws Exception {
        // given

        Member member = createMember("member1");

        repository.save(Todo.createTodo(new TodoRequest("hello1", LocalDateTime.now(), member)));
        repository.save(Todo.createTodo(new TodoRequest("hello2", LocalDateTime.now(), member)));
        repository.save(Todo.createTodo(new TodoRequest("hello3", LocalDateTime.now(), member)));
        repository.save(Todo.createTodo(new TodoRequest("hello4", LocalDateTime.now(), member)));

        // when

        PageRequest request = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "seq"));

        Page<Todo> page = repository.findByMember(memberRepository.findById("member1").get(), request);

        List<Todo> todoList = page.getContent();

        // then
        assertThat(todoList.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(4);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }
}