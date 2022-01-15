package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.MemberRepository;
import com.todo.domain.repository.TodoRepository;
import com.todo.dto.TodoDto;
import com.todo.dto.TodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public Long insert(String id, TodoRequest request) {

        Member member = memberRepository.findOne(id);
        request.setMember(member);
        Todo todo = Todo.createTodo(request);
        member.insertTodoList(todo);

        todoRepository.insert(todo);

        return todo.getSeq();
    }

    @Transactional(readOnly = true)
    public List<TodoDto> findTodos(String id) {

        return todoRepository
                .findTodos( memberRepository.findOne( id ) )
                .stream()
                .map( TodoDto::new )
                .collect( Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TodoDto findOne(Long seq) {

        return new TodoDto(todoRepository.findOne(seq));
    }

    public Todo update(Long seq, TodoRequest request) {

        Todo todo = todoRepository.findOne(seq);
        todo.changeTodoInfo(request);

        return todo;
    }

    public void delete(String id, Long seq) {

        Member member = memberRepository.findOne(id);
        Todo todo = todoRepository.findOne(seq);
        member.removeTodoList(todo);

        todoRepository.delete(seq);
    }
}
