package com.msousacode.bolao.web.dtos;

import javax.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull(message = "O username não deve ser nulo.") String username,
        @NotNull(message = "O password não deve ser nulo.") String password
) {
}
