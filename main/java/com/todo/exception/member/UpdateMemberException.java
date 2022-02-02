package com.todo.exception.member;

public class UpdateMemberException extends MemberException {

    public UpdateMemberException () {
        super();
    }

    public UpdateMemberException (String message) {
        super(message);
    }

    public UpdateMemberException (Throwable cause) {
        super(cause);
    }
}
