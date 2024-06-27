package com.lec.model.vo;

public enum ErrorCode {

    LECTURE_FULL("정원이 초과되어 수강 신청에 실패했습니다."),
    ALREADY_APPLIED("이미 신청된 특강입니다."),
    NO_DATA("해당 강의가 존재하지 않습니다."),
    FAIL("실패");

    public final String value;
    ErrorCode(String value){
        this.value = value;
    }
}
