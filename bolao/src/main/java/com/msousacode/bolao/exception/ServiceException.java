package com.msousacode.bolao.exception;

import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class ServiceException extends ResponseStatusException {

    public ServiceException(ServiceErrorsType erro) {
        super(erro.getStatus(), erro.getMsg());
    }

    public ServiceException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause){
        super(status, reason, cause);
    }
}
