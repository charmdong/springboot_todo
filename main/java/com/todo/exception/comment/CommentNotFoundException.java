package com.todo.exception.comment;

public class CommentNotFoundException extends CommentException {

    public CommentNotFoundException () {
        super();
    }

    public CommentNotFoundException (String message) {
        super(message);
    }

    public CommentNotFoundException (Throwable cause) {
        super(cause);
    }
}
