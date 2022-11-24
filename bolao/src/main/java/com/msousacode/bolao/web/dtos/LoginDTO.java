package com.msousacode.bolao.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public record LoginDTO(

        @JsonProperty("username")
        @NotEmpty(message = "O username não deve ser nulo.")
        String username,

        @JsonProperty("password")
        @NotEmpty(message = "O password não deve ser nulo.")
        String password
) {
}
