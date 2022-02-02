package com.todo.exception.comment;

public class DeleteCommentException extends CommentException {

    public DeleteCommentException () {
        super();
    }

    public DeleteCommentException (String message) {
        super(message);
    }

    public DeleteCommentException (Throwable cause) {
        super(cause);
    }
}

