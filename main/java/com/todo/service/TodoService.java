package com.todo.service;

import com.todo.domain.entity.Member;
import com.todo.domain.entity.Todo;
import com.todo.domain.repository.MemberRepository;
import com.todo.domain.repository.TodoRepository;
import com.todo.dto.TodoDto;
import com.todo.dto.TodoRequest;
import com.todo.exception.todo.DeleteTodoException;
import com.todo.exception.todo.InsertTodoException;
import com.todo.exception.todo.TodoNotFoundException;
import com.todo.exception.todo.UpdateTodoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public Long insert (String id, TodoRequest request) {

        Member member = memberRepository.findById(id)
                            .orElseThrow(() -> new InsertTodoException(
                                    new TodoNotFoundException("사용자 정보가 존재하지 않습니다.")
                            ));
        request.setMember(member);
        Todo todo = Todo.createTodo(request);
        member.insertTodoList(todo);

        try {
            todoRepository.save(todo);
        }
        catch (IllegalArgumentException ie) {
            throw new InsertTodoException(ie);
        }

        return todo.getSeq();
    }

    @Transactional(readOnly = true)
    public Page<TodoDto> findTodos (String id) {

        PageRequest request = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "seq"));
        Page<Todo> page = todoRepository.findByMember(memberRepository.findById(id).get(), request);
        Page<TodoDto> todoDtoPage = page.map(TodoDto::new);

        return todoDtoPage;
    }

    @Transactional(readOnly = true)
    public TodoDto findOne (Long seq) {

        Todo todo = todoRepository
                        .findBySeq(seq)
                        .orElseThrow(() -> new TodoNotFoundException("Todo 정보가 존재하지 않습니다."));

        return new TodoDto(todo);
    }

    public Todo update (Long seq, TodoRequest request) {

        Todo todo = todoRepository
                .findBySeq(seq)
                .orElseThrow(() -> new UpdateTodoException(
                        new TodoNotFoundException("Todo 정보가 존재하지 않습니다.")
                ));

        todo.changeTodoInfo(request);

        return todo;
    }

    public void delete (String id, Long seq) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new InsertTodoException(
                        new TodoNotFoundException("사용자 정보가 존재하지 않습니다.")
                ));

        Todo todo = todoRepository
                .findBySeq(seq)
                .orElseThrow(() -> new DeleteTodoException(
                        new TodoNotFoundException("Todo 정보가 존재하지 않습니다.")
                ));

        member.removeTodoList(todo);

        todoRepository.deleteBySeq(seq);
    }
}
