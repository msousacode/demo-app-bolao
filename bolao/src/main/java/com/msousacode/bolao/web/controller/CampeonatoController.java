package com.msousacode.bolao.web.controller;


import com.msousacode.bolao.persistence.entity.Campeonato;
import com.msousacode.bolao.service.CampeonatoService;
import com.msousacode.bolao.web.dtos.CampeonatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @Transactional
    @PostMapping("/bolao/{bolaoId}")
    public ResponseEntity<CampeonatoDTO> cadastrar(
            @RequestBody @Valid Campeonato campeonato,
            @NotEmpty(message = "O Id do Bolão não esta presente no path.") @PathVariable("bolaoId") @Valid UUID bolaoId) {

        var createResult = campeonatoService.cadastrar(campeonato, bolaoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createResult);
    }

    @GetMapping("/{campeonato_id}/partidas")
    public ResponseEntity<CampeonatoDTO> buscar(
            @PathVariable("campeonato_id") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID campeonatoId) {

        return ResponseEntity.ok().body(campeonatoService.buscar(campeonatoId));
    }
}
