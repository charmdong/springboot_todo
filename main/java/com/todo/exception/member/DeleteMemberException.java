package com.todo.exception.member;

public class DeleteMemberException extends MemberException {

    public DeleteMemberException () {
        super();
    }

    public DeleteMemberException (String message) {
        super(message);
    }

    public DeleteMemberException (Throwable cause) {
        super(cause);
    }
}
