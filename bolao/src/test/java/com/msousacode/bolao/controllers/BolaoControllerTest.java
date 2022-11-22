package com.msousacode.bolao.controllers;

import com.msousacode.bolao.web.dtos.BolaoDTO;
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
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BolaoControllerTest {

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
    void cadastrarBolao_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/boloes");

        var bolao = new BolaoDTO(null, "Bolao" + UUID.randomUUID(), "Descri", null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(bolao, headers);

        ResponseEntity<BolaoDTO> response = restTemplate.postForEntity(uri, request, BolaoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void buscarBolao_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/boloes/c7c69c9c-3650-4f49-9567-606c94807179/partidas");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<BolaoDTO> response = restTemplate.exchange(uri, HttpMethod.GET, request, BolaoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
