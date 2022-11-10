package com.msousacode.bolao.controllers;

import com.msousacode.bolao.dtos.CampeonatoDTO;
import com.msousacode.bolao.dtos.PartidaDTO;
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
public class PartidaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void cadastrarCampeonato_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/partidas/7ec58c41-3abb-4f41-bab7-caecd51ec304/campeonato");

        var partida = new PartidaDTO(null, "BRA", "ARG", null);

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, partida, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
