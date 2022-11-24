package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Campeonato;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CampeonatoDTO(

        @JsonProperty("campeonato_id")
        UUID uuid,

        @JsonProperty("nome")
        String nome,

        @JsonProperty("rodada")
        Integer rodada,

        @JsonProperty("dataInicio")
        LocalDate dataInicio,

        @JsonProperty("bolao")
        BolaoDTO bolaoDTO,

        @JsonProperty("partidas")
        List<PartidaDTO> partidaDTO
) {
    public CampeonatoDTO(Campeonato campeonato) {
        this(
                campeonato.getId(),
                campeonato.getNome(),
                campeonato.getRodada(),
                campeonato.getDataInicio(),
                new BolaoDTO(campeonato.getBolao()),
                campeonato.getPartidas().stream().map(PartidaDTO::new).toList()
        );
    }
}
