package com.msousacode.bolao.service;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.Campeonato;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.util.BeanUtil;
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

    public CampeonatoDTO buscar(UUID uuid) {

        var campeonato = campeonatoRepository.findById(uuid)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        return new CampeonatoDTO(campeonato);
    }

    @Transactional
    public CampeonatoDTO cadastrar(CampeonatoDTO campeonatoDTO, UUID bolaoId) {

        var bolao = bolaoService.buscar(bolaoId);

        Campeonato campeonato = associarCampeonatoBolao(campeonatoDTO, bolao);

        var result = campeonatoRepository.save(campeonato);

        return new CampeonatoDTO(result);
    }

    private static Campeonato associarCampeonatoBolao(CampeonatoDTO campeonatoDTO, Bolao bolao) {

        var campeonato = (Campeonato) BeanUtil.convert(campeonatoDTO, new Campeonato());

        campeonato.setBolao(bolao);

        return campeonato;
    }
}