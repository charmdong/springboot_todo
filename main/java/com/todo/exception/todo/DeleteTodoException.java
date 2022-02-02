package com.todo.exception.todo;

public class DeleteTodoException extends TodoException {

    public DeleteTodoException () {
        super();
    }

    public DeleteTodoException (String message) {
        super(message);
    }

    public DeleteTodoException (Throwable cause) {
        super(cause);
    }
}
