package com.todo.exception.comment;

public class CommentException extends RuntimeException {

    public CommentException () {
    }

    public CommentException (String message) {
        super(message);
    }

    public CommentException (Throwable cause) {
        super(cause);
    }
}
