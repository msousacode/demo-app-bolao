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

        var campeonato = new CampeonatoDTO(null, "Campeonato " + UUID.randomUUID(), 1, LocalDate.now(), null, null);

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos/bolao/7df1719e-a925-4fbf-8c9a-4e672f697b44");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(campeonato, headers);

        ResponseEntity<CampeonatoDTO> response = restTemplate.postForEntity(uri, request, CampeonatoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void buscarCampenatosComAsPartidas() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos/28240d50-d8ed-4e56-9b0a-3ec12d4a7ce4/partidas");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<CampeonatoDTO> response = restTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
        });

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
