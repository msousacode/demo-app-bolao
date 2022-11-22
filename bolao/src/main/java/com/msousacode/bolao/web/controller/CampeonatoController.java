package com.msousacode.bolao.web.controller;


import com.msousacode.bolao.exception.ResourceNotFoundException;
import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Campeonato;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.repository.BolaoRepository;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.web.dtos.CampeonatoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private BolaoRepository bolaoRepository;

    @Transactional
    @PostMapping("/bolao/{bolaoId}")
    public ResponseEntity<CampeonatoDTO> cadastrar(
            @RequestBody @Valid CampeonatoDTO campeonatoDTO,
            @NotEmpty(message = "O Id do Bolão não esta presente no path.") @PathVariable("bolaoId") @Valid UUID bolaoId) {

        var campeonato = new Campeonato();
        BeanUtils.copyProperties(campeonatoDTO, campeonato);

        var bolao = bolaoRepository.findById(bolaoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        campeonato.setBolao(bolao);

        var result = campeonatoRepository.save(campeonato);

        var response = new CampeonatoDTO(
                                result.getId(),
                                result.getNome(),
                                result.getRodada(), result.getDataInicio(), List.of(), result.getBolao());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{campeonato_id}/partidas")
    public ResponseEntity<CampeonatoDTO> buscar(
            @PathVariable("campeonato_id") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID uuid) {

        var campeonato = campeonatoRepository.findById(uuid);

        if (campeonato.isPresent())
            return ResponseEntity.ok(new CampeonatoDTO(campeonato.get()));
        else
            throw new ResourceNotFoundException(ServiceErrorsType.RESOURCE_NOT_FOUND.getMsg());
    }
}
