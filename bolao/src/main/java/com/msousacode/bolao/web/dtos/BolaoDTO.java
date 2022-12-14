package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.Campeonato;

import java.util.List;
import java.util.UUID;

public record BolaoDTO(

        @JsonProperty("bolao_id")
        UUID id,

        @JsonProperty("nome")
        String nome,

        @JsonProperty("descricao")
        String descricao,

        @JsonProperty("campeonato")
        CampeonatoBolaoDTO campeonatoDTO

) {
    public BolaoDTO(Bolao bolao, Campeonato campeonato) {
        this(
                bolao.getId(),
                bolao.getNome(),
                bolao.getDescricao(),
                new CampeonatoBolaoDTO(campeonato)
        );
    }

    public BolaoDTO(Bolao bolao) {
        this(
                bolao.getId(),
                bolao.getNome(),
                bolao.getDescricao(),
                null
        );
    }
}
