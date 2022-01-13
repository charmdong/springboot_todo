package com.todo.api;

import com.todo.domain.entity.Comment;
import com.todo.domain.entity.Todo;
import com.todo.dto.CommentRequest;
import com.todo.dto.TodoRequest;
import com.todo.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity findTodoList (@PathVariable("id") String id) {

        List<Todo> todoList = todoService.findTodos( id );

        return new ResponseEntity( todoList, HttpStatus.OK );
    }

    @GetMapping("/{seq}")
    public ResponseEntity findTodo (@PathVariable("seq") Long seq) {

        Todo todo = todoService.findOne( seq );

        return new ResponseEntity( todo, HttpStatus.OK );
    }

    @PostMapping("/{id}")
    public ResponseEntity addTodo (@PathVariable("id") String id, TodoRequest request) {

        Long seq = todoService.insert( id, request );

        return new ResponseEntity( seq, HttpStatus.OK );
    }

    @PatchMapping("/{seq}")
    public ResponseEntity updateTodo (@PathVariable("seq") Long seq, TodoRequest request) {

        return new ResponseEntity( todoService.update( seq, request ), HttpStatus.OK );
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity deleteTodo (@PathVariable("seq") Long seq, @RequestParam("id") String id) {

        todoService.delete( id, seq );

        return new ResponseEntity( HttpStatus.OK );
    }

    // comment

    @GetMapping("/{seq}/comments")
    public ResponseEntity findCommentList (@PathVariable("seq") Long seq) {

        Todo todo = todoService.findOne( seq );
        List<Comment> commentList = todo.getCommentList();

        return new ResponseEntity( commentList, HttpStatus.OK );
    }

    @PostMapping("/{seq}/comments")
    public ResponseEntity addComment (@PathVariable("seq") Long seq, CommentRequest request) {

        Long commentSeq = commentService.insert( seq, request );

        return new ResponseEntity( commentSeq, HttpStatus.OK );
    }

    @PatchMapping("/comments/{commentSeq}")
    public ResponseEntity updateComment (@PathVariable("seq") Long seq, @PathVariable("commentSeq") Long commentSeq, CommentRequest request) {

        Comment comment = commentService.update( commentSeq, request );

        return new ResponseEntity( comment, HttpStatus.OK );
    }

    @DeleteMapping("/{seq}/comments/{commentSeq}")
    public ResponseEntity deleteComment (@PathVariable("seq") Long seq, @PathVariable("commentSeq") Long commentSeq) {

        commentService.delete( seq, commentSeq );

        return new ResponseEntity( HttpStatus.OK );
    }
}
