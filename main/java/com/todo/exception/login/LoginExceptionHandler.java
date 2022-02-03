package com.todo.exception.login;

import com.todo.api.LoginController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {LoginController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoginExceptionHandler {

    @ExceptionHandler
    public ResponseEntity loginExceptionHandler(LoginException le) {

        return new ResponseEntity(le.getMessage(), HttpStatus.OK);
    }
}
