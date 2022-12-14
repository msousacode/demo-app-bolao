package com.msousacode.bolao.web.controller;

import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.service.BolaoService;
import com.msousacode.bolao.web.dtos.BolaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/boloes")
public class BolaoController {

    @Autowired
    private BolaoService bolaoService;

    @PostMapping
    public ResponseEntity<BolaoDTO> cadastrar(@RequestBody @Valid Bolao bolao, Principal principal) {

        var response = bolaoService.cadastrar(bolao, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{bolaoId}/campeonato/partidas")
    public ResponseEntity<BolaoDTO> buscarBolao(
            @PathVariable("bolaoId") @Valid @NotEmpty(message = "ID bolão não esta presente no path.") UUID bolaoId) {

        return ResponseEntity.status(HttpStatus.OK).body(bolaoService.buscarBolao(bolaoId));
    }
}
