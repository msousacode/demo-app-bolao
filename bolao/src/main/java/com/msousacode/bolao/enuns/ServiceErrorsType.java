package com.msousacode.bolao.enuns;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceErrorsType {

    INVALID_ACCESS(HttpStatus.FORBIDDEN, "invalid.access"),
    NEW_PASS_WORD_REQUIRED(HttpStatus.PRECONDITION_FAILED, "change.password.required");

    private final HttpStatus status;
    private final String msg;

    ServiceErrorsType(HttpStatus status, String msg1) {
        this.status = status;
        this.msg = msg1;
    }
}
