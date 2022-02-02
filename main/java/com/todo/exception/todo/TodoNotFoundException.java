package com.todo.exception.todo;

public class TodoNotFoundException extends TodoException {

    public TodoNotFoundException () {
        super();
    }

    public TodoNotFoundException (String message) {
        super(message);
    }

    public TodoNotFoundException (Throwable cause) {
        super(cause);
    }
}
