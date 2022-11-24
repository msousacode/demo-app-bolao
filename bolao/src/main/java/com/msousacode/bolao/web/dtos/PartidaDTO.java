package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msousacode.bolao.persistence.entity.Partida;
import com.msousacode.bolao.persistence.entity.types.PartidaStatusType;

import java.util.UUID;

public record PartidaDTO(

        @JsonProperty("partidaId")
        UUID id,

        @JsonProperty("time1")
        String time1,

        @JsonProperty("time2")
        String time2,

        @JsonProperty("resultadoTime1")
        Integer resultadoTime1,

        @JsonProperty("resultadoTime2")
        Integer resultadoTime2,

        @JsonProperty("status")
        PartidaStatusType status
) {
    public PartidaDTO(Partida partida) {
        this(
                partida.getId(),
                partida.getTime1(),
                partida.getTime2(),
                partida.getResultadoTime1(),
                partida.getResultadoTime2(),
                partida.getStatus()
        );
    }
}
