package com.msousacode.bolao.controllers;

import com.msousacode.bolao.web.dtos.CampeonatoDTO;
import com.msousacode.bolao.web.dtos.LoginDTO;
import com.msousacode.bolao.web.dtos.TokenIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampeonatoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String token;

    //Realiza o login e recupera o token para realização dos testes de unidade.
    @BeforeEach
    public void setUp() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/login");

        var login = new LoginDTO("msousacode@gmail.com", "NovaSenha@123");

        ResponseEntity<TokenIdDTO> response = restTemplate.postForEntity(uri, login, TokenIdDTO.class);

        token = Objects.requireNonNull(response.getBody()).token();
    }

    @Test
    void cadastrarCampeonato_entaoSucesso() throws Exception {

        var campeonato = new CampeonatoDTO(null, "Campeonato " + UUID.randomUUID(), 1, LocalDate.now(), List.of());

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(campeonato, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, request, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void buscarCampenatosComAsPartidas() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos/dcffed1f-b12e-4f20-9002-20d908c753d8");

        ResponseEntity<CampeonatoDTO> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
