package com.msousacode.bolao.controllers;

import com.msousacode.bolao.web.dtos.CampeonatoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampeonatoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void cadastrarCampeonato_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos");

        var campeonato = new CampeonatoDTO(null, "Campeonato " + UUID.randomUUID(), 1, LocalDate.now(), List.of());

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, campeonato, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void buscarCampenatosComAsPartidas() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/campeonatos/dcffed1f-b12e-4f20-9002-20d908c753d8");

        ResponseEntity<CampeonatoDTO> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
