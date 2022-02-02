package com.todo.exception.member;

public class MemberNotFoundException extends MemberException {

    public MemberNotFoundException () {
        super();
    }

    public MemberNotFoundException (String message) {
        super(message);
    }

    public MemberNotFoundException (Throwable cause) {
        super(cause);
    }
}
