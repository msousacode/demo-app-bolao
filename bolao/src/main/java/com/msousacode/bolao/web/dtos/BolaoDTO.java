package com.msousacode.bolao.web.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

public record BolaoDTO(
        UUID id,
        @NotEmpty(message = "Nonme do bolão não deve ser vazio.") String nome,
        @Size(max = 255, message = "Descrição não deve ultrapassar 255 caracteres.") String descricao
        //TODO implementar o usuario que cria o bolão é o dono.
) {
}
