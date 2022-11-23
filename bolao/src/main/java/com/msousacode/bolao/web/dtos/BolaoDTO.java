package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.Campeonato;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

public record BolaoDTO(

        @JsonProperty("bolao_id")
        UUID id,

        @JsonProperty("nome")
        @NotEmpty(message = "Nonme do bolão não deve ser vazio.")
        String nome,

        @JsonProperty("descricao")
        @Size(max = 255, message = "Descrição não deve ultrapassar 255 caracteres.")
        String descricao,

        @JsonProperty("campeonato")
        CampeonatoDTO campeonatoDTO

) {
    public BolaoDTO(Bolao bolao, Campeonato campeonato) {
        this(
                bolao.getId(),
                bolao.getNome(),
                bolao.getDescricao(),
                new CampeonatoDTO(campeonato)
        );
    }
}
