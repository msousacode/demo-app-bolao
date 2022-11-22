package com.msousacode.bolao.web.controller;

import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.repository.BolaoRepository;
import com.msousacode.bolao.persistence.repository.UsuarioRepository;
import com.msousacode.bolao.web.dtos.BolaoDTO;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boloes")
public class BolaoController {

    @Autowired
    private BolaoRepository bolaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<BolaoDTO> cadastrar(@RequestBody @Valid BolaoDTO bolaoDTO, Principal principal) {

        //TODO Refatorar!!!
        //TODO Gerar o Link do Bolao.

        String username = principal.getName();

        var user = usuarioRepository.findByUserName(username);

        var bolao = new Bolao();
        BeanUtils.copyProperties(bolaoDTO, bolao);

        bolao.getUsuarios().add(user.get());

        var result = bolaoRepository.save(bolao);

        var response = new BolaoDTO(result.getId(), result.getNome(), result.getDescricao());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
