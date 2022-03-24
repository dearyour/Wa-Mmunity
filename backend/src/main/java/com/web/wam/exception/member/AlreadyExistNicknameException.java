package com.web.wam.exception.member;

public class AlreadyExistNicknameException extends RuntimeException{

    private static final String MESSAGE = "이미 등록된 닉네임 입니다.";
    public AlreadyExistNicknameException () {
        super(MESSAGE);
    }
}
