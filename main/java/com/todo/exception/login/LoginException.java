package com.todo.exception.login;

public class LoginException extends RuntimeException {

    public LoginException () {
    }

    public LoginException (String message) {
        super(message);
    }

    public LoginException (Throwable cause) {
        super(cause);
    }
}
