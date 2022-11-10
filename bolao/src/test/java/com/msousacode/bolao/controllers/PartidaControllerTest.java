package com.msousacode.bolao.controllers;

import com.msousacode.bolao.dtos.PartidaDTO;
import com.msousacode.bolao.enuns.PartidaStatusType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartidaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void cadastrarCampeonato_entaoSucesso() throws Exception {

        URI uri = new URI("http://localhost:" + port + "/api/partidas/c7f20048-0f9f-42c0-95bd-d0f65faade89/campeonato");

        var partida = new PartidaDTO(null, "BRA", "ARG", 0, 0, PartidaStatusType.NAO_FINALIZADA);

        ResponseEntity<Void> response = restTemplate.postForEntity(uri, partida, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
