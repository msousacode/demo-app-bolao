package com.msousacode.bolao.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CampeonatoDTO(
        UUID uuid,
        @NotEmpty(message = "Nome do Campeonato não pode ser vazia.") String nome,
        @NotNull(message = "Rodada não pode ser nula.") Integer rodada,
        @NotNull(message = "Data de inicio do campeonato não deve ser nula.") LocalDate dataInicio
) {
}
