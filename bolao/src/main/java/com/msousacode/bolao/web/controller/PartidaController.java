package com.msousacode.bolao.web.controller;


import com.msousacode.bolao.persistence.entity.Partida;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.persistence.repository.PartidaRepository;
import com.msousacode.bolao.web.dtos.PartidaDTO;
import org.springframework.beans.BeanUtils;
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
    private PartidaRepository partidaRepository;//TODO depois criar a Service e distribuir a lógica.

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Transactional
    @PostMapping("/campeonato/{campeonato_id}")
    public ResponseEntity<PartidaDTO> cadastrar(
            @RequestBody @Valid PartidaDTO partidaDTO,
            @PathVariable("campeonato_id") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID uuid) throws Exception {

        var campeonato = campeonatoRepository.findById(uuid);

        if (campeonato.isPresent()) {

            var partida = new Partida(campeonato.get());

            BeanUtils.copyProperties(partidaDTO, partida);

            var result = partidaRepository.save(partida);

            var response = new PartidaDTO(result);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            throw new Exception("Não foi encontrado o recurso para o uuid: " + uuid);//TODO pensar em algo melhor.
        }
    }
}
