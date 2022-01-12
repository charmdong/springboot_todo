package com.todo.api;

import com.todo.domain.entity.Todo;
import com.todo.dto.TodoRequest;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoApiController {

    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity findTodoList (@PathVariable("id") String id) {

        List<Todo> todoList = todoService.findTodos(id);

        return new ResponseEntity(todoList, HttpStatus.OK);
    }

    @GetMapping("/{seq}")
    public ResponseEntity findTodo (@PathVariable("seq") Long seq) {

        Todo todo = todoService.findOne(seq);

        return new ResponseEntity(todo, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity addTodo(@PathVariable("id") String id, TodoRequest request) {

        Long seq = todoService.insert(id, request);

        return new ResponseEntity(seq, HttpStatus.OK);
    }

    @PatchMapping("/{seq}")
    public ResponseEntity updateTodo(@PathVariable("seq") Long seq, TodoRequest request) {

        return new ResponseEntity(todoService.update(seq, request), HttpStatus.OK);
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity deleteTodo(@PathVariable("seq") Long seq, @RequestParam("id") String id) {

        todoService.delete(id, seq);

        return new ResponseEntity(HttpStatus.OK);
    }
}
