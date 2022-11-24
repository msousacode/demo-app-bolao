package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Campeonato;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CampeonatoDTO(

        @JsonProperty("campeonato_id")
        UUID uuid,

        @JsonProperty("nome")
        @NotEmpty(message = "Nome do Campeonato não pode ser vazia.")
        String nome,

        @JsonProperty("rodada")
        @NotNull(message = "Rodada não pode ser nula.")
        Integer rodada,

        @JsonProperty("dataInicio")
        @NotNull(message = "Data de inicio do campeonato não deve ser nula.")
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
