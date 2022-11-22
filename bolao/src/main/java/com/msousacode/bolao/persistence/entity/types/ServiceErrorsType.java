package com.msousacode.bolao.persistence.entity.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceErrorsType {

    INVALID_ACCESS(HttpStatus.FORBIDDEN, "invalid.access"),
    NEW_PASS_WORD_REQUIRED(HttpStatus.PRECONDITION_FAILED, "change.password.required"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "resource.not.found"),
    ERROR_WHEN_RECORDING(HttpStatus.BAD_REQUEST, "error.when.recording"),
    OBJECT_CONVERSION_ERROR(HttpStatus.BAD_REQUEST, "object.conversion.error");

    private final HttpStatus status;
    private final String msg;

    ServiceErrorsType(HttpStatus status, String msg1) {
        this.status = status;
        this.msg = msg1;
    }
}
