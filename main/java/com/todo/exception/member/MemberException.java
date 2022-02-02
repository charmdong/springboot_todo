package com.todo.exception.member;

public class MemberException extends RuntimeException {

    public MemberException () {
    }

    public MemberException (String message) {
        super(message);
    }

    public MemberException (Throwable cause) {
        super(cause);
    }
}
