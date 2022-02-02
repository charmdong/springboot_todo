package com.todo.exception.todo;

import com.todo.api.TodoApiController;
import com.todo.exception.comment.CommentException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {TodoApiController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TodoExceptionHandler {

    @ExceptionHandler
    public ResponseEntity todoExceptionHandler(TodoException te) {

        return new ResponseEntity(te.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity commentExceptionHandler(CommentException ce) {

        return new ResponseEntity(ce.getMessage(), HttpStatus.OK);
    }
}
