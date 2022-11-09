package com.msousacode.bolao.controllers;


import com.msousacode.bolao.dtos.CampeonatoDTO;
import com.msousacode.bolao.entities.Campeonato;
import com.msousacode.bolao.repositories.CampeonatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoRepository campeonatoRepository;//TODO depois criar a Service e distribuir a lógica.

    @Transactional
    @PostMapping
    public ResponseEntity<CampeonatoDTO> cadastrar(@RequestBody @Valid CampeonatoDTO campeonatoDTO) {

        var campeonato = new Campeonato();
        BeanUtils.copyProperties(campeonatoDTO, campeonato);

        var result = campeonatoRepository.save(campeonato);

        var response = new CampeonatoDTO(result.getId(), result.getNome(), result.getRodada(), result.getDataInicio());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
