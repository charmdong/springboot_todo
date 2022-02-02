package com.todo.exception.todo;

public class InsertTodoException extends TodoException {

    public InsertTodoException () {
        super();
    }

    public InsertTodoException (String message) {
        super(message);
    }

    public InsertTodoException (Throwable cause) {
        super(cause);
    }
}
