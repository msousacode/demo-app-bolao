package com.msousacode.bolao.web.controller;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.BolaoUsuario;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.entity.types.UsuarioType;
import com.msousacode.bolao.persistence.repository.BolaoRepository;
import com.msousacode.bolao.persistence.repository.BolaoUsuarioRepository;
import com.msousacode.bolao.persistence.repository.UsuarioRepository;
import com.msousacode.bolao.service.BolaoService;
import com.msousacode.bolao.web.dtos.BolaoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/boloes")
public class BolaoController {

    @Autowired
    private BolaoService bolaoService;

    @PostMapping
    public ResponseEntity<BolaoDTO> cadastrar(@RequestBody @Valid BolaoDTO bolaoDTO, Principal principal) {
        try {
            var response = bolaoService.cadastrar(bolaoDTO, principal.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception ex){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ServiceErrorsType.ERROR_WHEN_RECORDING.getMsg(), ex.getCause());
        }
    }
}
