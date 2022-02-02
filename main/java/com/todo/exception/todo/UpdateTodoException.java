package com.todo.exception.todo;

public class UpdateTodoException extends TodoException {

    public UpdateTodoException () {
        super();
    }

    public UpdateTodoException (String message) {
        super(message);
    }

    public UpdateTodoException (Throwable cause) {
        super(cause);
    }
}
