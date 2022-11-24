package com.msousacode.bolao.service;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Campeonato;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.web.dtos.CampeonatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private BolaoService bolaoService;

    public CampeonatoDTO buscar(UUID campeonatoId) {

        var campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        return new CampeonatoDTO(campeonato);
    }

    @Transactional
    public CampeonatoDTO cadastrar(Campeonato campeonato, UUID bolaoId) {

        var bolao = bolaoService.buscar(bolaoId);

        /*
         * Vínculo entre Campeonato e Bolão.
         */
        campeonato.setBolao(bolao);

        var result = campeonatoRepository.save(campeonato);

        return new CampeonatoDTO(result);
    }
}