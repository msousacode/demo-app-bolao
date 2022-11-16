package com.msousacode.bolao.exception;

import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import org.springframework.web.server.ResponseStatusException;

public class ServiceException extends ResponseStatusException {

    public  ServiceException(ServiceErrorsType erro) {
        super(erro.getStatus(), erro.getMsg());
    }
}
