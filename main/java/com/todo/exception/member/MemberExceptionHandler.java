package com.todo.exception.member;

import com.todo.api.MemberApiController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {MemberApiController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MemberExceptionHandler {

    @ExceptionHandler
    public ResponseEntity exceptionHandler(Exception e) {

        return new ResponseEntity(e.getMessage(), HttpStatus.OK);
    }
}
