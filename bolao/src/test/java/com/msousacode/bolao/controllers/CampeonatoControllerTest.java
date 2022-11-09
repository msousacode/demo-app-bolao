package com.msousacode.bolao.controllers;

import com.msousacode.bolao.dtos.CampeonatoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
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

        var campeonato = new CampeonatoDTO(null, "Campeonato " + UUID.randomUUID(), 1, LocalDate.now());

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, campeonato, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
