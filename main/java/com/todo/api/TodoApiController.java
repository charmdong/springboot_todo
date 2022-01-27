package com.todo.api;

import com.todo.dto.CommentDto;
import com.todo.dto.CommentRequest;
import com.todo.dto.TodoDto;
import com.todo.dto.TodoRequest;
import com.todo.service.CommentService;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoApiController {

    private final TodoService todoService;
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity findTodoList (String id) {

        Page<TodoDto> todoList = todoService.findTodos(id);

        return new ResponseEntity(todoList, HttpStatus.OK);
    }

    @GetMapping("/{seq}")
    public ResponseEntity findTodo (@PathVariable("seq") Long seq) {

        TodoDto todo = todoService.findOne(seq);

        return new ResponseEntity(todo, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity addTodo (@PathVariable("id") String id, TodoRequest request) {

        Long seq = todoService.insert(id, request);

        return new ResponseEntity(seq, HttpStatus.OK);
    }

    @PatchMapping("/{seq}")
    public ResponseEntity updateTodo (@PathVariable("seq") Long seq, TodoRequest request) {

        return new ResponseEntity(todoService.update(seq, request), HttpStatus.OK);
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity deleteTodo (@PathVariable("seq") Long seq, @RequestParam("id") String id) {

        todoService.delete(id, seq);

        return new ResponseEntity(HttpStatus.OK);
    }

    // comment

    @PostMapping("/{seq}/comments")
    public ResponseEntity addComment (@PathVariable("seq") Long seq, CommentRequest request) {

        Long commentSeq = commentService.insert(seq, request);

        return new ResponseEntity(commentSeq, HttpStatus.OK);
    }

    @PatchMapping("/comments/{commentSeq}")
    public ResponseEntity updateComment (@PathVariable("seq") Long seq, @PathVariable("commentSeq") Long commentSeq, CommentRequest request) {

        CommentDto comment = commentService.update(commentSeq, request);

        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{seq}/comments/{commentSeq}")
    public ResponseEntity deleteComment (@PathVariable("seq") Long seq, @PathVariable("commentSeq") Long commentSeq) {

        commentService.delete(seq, commentSeq);

        return new ResponseEntity(HttpStatus.OK);
    }
}
