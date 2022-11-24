package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Campeonato;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CampeonatoBolaoDTO(

        @JsonProperty("campeonato_id")
        UUID uuid,

        @JsonProperty("nome")
        String nome,

        @JsonProperty("rodada")
        Integer rodada,

        @JsonProperty("dataInicio")
        LocalDate dataInicio,

        @JsonProperty("partidas")
        List<PartidaDTO> partidaDTO
) {
    public CampeonatoBolaoDTO(Campeonato campeonato) {
        this(
                campeonato.getId(),
                campeonato.getNome(),
                campeonato.getRodada(),
                campeonato.getDataInicio(),
                campeonato.getPartidas() == null ? List.of() : campeonato.getPartidas().stream().map(PartidaDTO::new).toList()
        );
    }
}
