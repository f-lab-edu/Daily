package com.flab.daily.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*공통*/
    INVALID_INPUT_PARAMETER(700, "잘못된 파라미터 값입니다."),
    INVALID_DATATYPE(701, "잘못된 데이터 타입입니다."),

    /*유효하지 않는 데이터인 경우*/
    NOT_FOUND_CATEGORY(900,"유효하지 않은 카테고리입니다."),
    NOT_FOUND_EMAIL(900,"유효하지 않는 Email입니다."),
    NOT_FOUND_MEETING(900,"유효하지 않는 소모임입니다."),
    INVALID_ACCOUNT_USER(900, "아이디와 비밀번호가 일치하지 않습니다."),

    /*중복된 데이터인 경우*/
    VALUE_ALREADY_EXISTS(901, "이미 존재하는 데이터입니다."),
    IS_EXIST_USER_BY_EMAIL(901, "중복된 이메일입니다."),
    DUPLICATED_BY_CATEGORY_NAME(901, "중복된 카테고리 이름입니다."),

    /*데이터가 초과하는 경우*/
    INDEX_OUT_OF_PEOPLE(902, "모집 인원 수는 신청자 수보다 적을 수 없습니다."),

    /*JWT*/
    INVALID_TOKEN(800, "유효하지 않은 토큰입니다."),
    NOT_EXIST_TOKEN(800, "토큰이 존재하지 않습니다."),
    INVALID_ACCESS(800, "잘못된 접근입니다.");

    private final int code;
    private final String message;

}
