package com.msousacode.bolao.dtos;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public record PartidaDTO(
        UUID id,
        @NotEmpty(message = "Time 1 não deve ser vazio.") String time1,
        @NotEmpty(message = "Time 2 não deve ser vazio.") String time2,
        CampeonatoDTO campeonatoDTO
) {
}
