package com.msousacode.bolao.controllers;


import com.msousacode.bolao.dtos.CampeonatoDTO;
import com.msousacode.bolao.dtos.PartidaDTO;
import com.msousacode.bolao.entities.Campeonato;
import com.msousacode.bolao.entities.Partida;
import com.msousacode.bolao.repositories.CampeonatoRepository;
import com.msousacode.bolao.repositories.PartidaRepository;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @PostMapping("/{campeonato_id}/campeonato")//TODO verificar se faz sentido o path dessa maneira
    public ResponseEntity<PartidaDTO> cadastrar(
            @RequestBody @Valid PartidaDTO partidaDTO,
            @PathVariable("campeonato_id") @NotEmpty(message = "ID do Campeonato deve ser informado") @Valid UUID uuid) throws Exception {

            var campeonato = campeonatoRepository.findById(uuid);

            if(campeonato.isPresent()) {

                var partida = new Partida(campeonato.get());

                BeanUtils.copyProperties(partidaDTO, partida);

                var result = partidaRepository.save(partida);

                var response = new PartidaDTO(result.getId(), result.getTime1(), result.getTime2(), new CampeonatoDTO(result.getCampeonato()));

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                throw new Exception("Não foi encontrado o recurso para o uuid: " + uuid);//TODO pensar em algo melhor.
            }
    }
}
