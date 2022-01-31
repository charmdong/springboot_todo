package com.todo.exception;

import com.todo.api.TodoApiController;
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
    public ResponseEntity exceptionHandler(Exception e) {

        return new ResponseEntity(e.getMessage(), HttpStatus.OK);
    }
}
