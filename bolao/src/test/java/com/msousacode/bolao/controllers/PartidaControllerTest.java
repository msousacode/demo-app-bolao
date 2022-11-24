package com.msousacode.bolao.controllers;

import com.msousacode.bolao.web.dtos.LoginDTO;
import com.msousacode.bolao.web.dtos.PartidaDTO;
import com.msousacode.bolao.persistence.entity.types.PartidaStatusType;
import com.msousacode.bolao.web.dtos.TokenIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartidaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/login");

        var login = new LoginDTO("msousacode@gmail.com", "NovaSenha@123");

        ResponseEntity<TokenIdDTO> response = restTemplate.postForEntity(uri, login, TokenIdDTO.class);

        token = Objects.requireNonNull(response.getBody()).token();
    }

    @Test
    void cadastrarPartida_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/partidas/campeonato/28240d50-d8ed-4e56-9b0a-3ec12d4a7ce4");

        var partida = new PartidaDTO(null, "BRA", "ARG", 0, 0, PartidaStatusType.NAO_FINALIZADA);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(partida, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, request, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
