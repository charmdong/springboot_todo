package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.MemberJpaRepository;
import com.todo.domain.repository.TodoJpaRepository;
import com.todo.dto.TodoDto;
import com.todo.dto.TodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class TodoService {

    private final TodoJpaRepository todoRepository;
    private final MemberJpaRepository memberRepository;

    public Long insert (String id, TodoRequest request) {

        Member member = memberRepository.findById( id ).get();
        request.setMember( member );
        Todo todo = Todo.createTodo( request );
        member.insertTodoList( todo );

        todoRepository.save( todo );

        return todo.getSeq();
    }

    @Transactional(readOnly = true)
    public Page<TodoDto> findTodos (String id) {

        PageRequest request = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "seq"));
        Page<Todo> page = todoRepository.findByMember(memberRepository.findById( id ).get(), request);
        Page<TodoDto> todoDtoPage = page.map(TodoDto::new);

        return todoDtoPage;
    }

    @Transactional(readOnly = true)
    public TodoDto findOne (Long seq) {

        return new TodoDto( todoRepository.findBySeq( seq ) );
    }

    public Todo update (Long seq, TodoRequest request) {

        Todo todo = todoRepository.findBySeq( seq );
        todo.changeTodoInfo( request );

        return todo;
    }

    public void delete (String id, Long seq) {

        Member member = memberRepository.findById( id ).get();
        Todo todo = todoRepository.findBySeq( seq );
        member.removeTodoList( todo );

        todoRepository.deleteBySeq( seq );
    }
}
