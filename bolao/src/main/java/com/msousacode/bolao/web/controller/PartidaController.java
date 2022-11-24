package com.msousacode.bolao.web.controller;


import com.msousacode.bolao.persistence.entity.Partida;
import com.msousacode.bolao.service.PartidaService;
import com.msousacode.bolao.web.dtos.PartidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @Transactional
    @PostMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<PartidaDTO> cadastrar(
            @RequestBody @Valid Partida partida,
            @PathVariable("campeonatoId") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID campeonatoId) throws Exception {

        var createResult = partidaService.cadastar(partida, campeonatoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createResult);
    }
}
