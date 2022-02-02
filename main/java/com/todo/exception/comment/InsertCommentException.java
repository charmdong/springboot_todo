package com.todo.exception.comment;

public class InsertCommentException extends CommentException {

    public InsertCommentException () {
        super();
    }

    public InsertCommentException (String message) {
        super(message);
    }

    public InsertCommentException (Throwable cause) {
        super(cause);
    }
}
