package com.msousacode.bolao.exceptions;

import com.msousacode.bolao.enuns.ServiceErrorsType;
import org.springframework.web.server.ResponseStatusException;

public class ServiceException extends ResponseStatusException {

    public ServiceException(ServiceErrorsType erro) {
        super(erro.getStatus(), erro.getMsg());
    }
}
