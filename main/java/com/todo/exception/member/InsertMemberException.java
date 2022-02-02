package com.todo.exception.member;

public class InsertMemberException extends MemberException {

    public InsertMemberException () {
        super();
    }

    public InsertMemberException (String message) {
        super(message);
    }

    public InsertMemberException (Throwable cause) {
        super(cause);
    }
}
