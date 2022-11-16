package com.msousacode.bolao.web.dtos;

import com.msousacode.bolao.persistence.entity.Partida;
import com.msousacode.bolao.persistence.entity.types.PartidaStatusType;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public record PartidaDTO(
        UUID id,
        @NotEmpty(message = "Time 1 não deve ser vazio.") String time1,
        @NotEmpty(message = "Time 2 não deve ser vazio.") String time2,
        Integer resultadoTime1,
        Integer resultadoTime2,
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
