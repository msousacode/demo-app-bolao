package com.msousacode.bolao.service;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Partida;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.persistence.repository.PartidaRepository;
import com.msousacode.bolao.web.dtos.PartidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Transactional
    public PartidaDTO cadastar(Partida partida, UUID campeonatoId) {

        var campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        /*
         * Realiza vínculo entre Partida e Campeonato.
         */
        partida.setCampeonato(campeonato);

        var createResult = partidaRepository.save(partida);

        return new PartidaDTO(createResult);
    }
}
