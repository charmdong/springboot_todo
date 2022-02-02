package com.todo.exception.comment;

public class UpdateCommentException extends CommentException {

    public UpdateCommentException () {
        super();
    }

    public UpdateCommentException (String message) {
        super(message);
    }

    public UpdateCommentException (Throwable cause) {
        super(cause);
    }
}
