package com.web.wam.exception.member;

public class NotFoundMemberException extends RuntimeException {

    private static final String MESSAGE = "유저를 찾지 못했습니다.";
    public NotFoundMemberException() {super(MESSAGE);}
}
