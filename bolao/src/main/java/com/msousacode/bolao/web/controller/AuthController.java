package com.msousacode.bolao.web.controller;

import com.msousacode.bolao.service.CognitoService;
import com.msousacode.bolao.web.dtos.LoginDTO;
import com.msousacode.bolao.web.dtos.TokenIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/login")
public class AuthController {

    @Autowired
    private CognitoService conCognitoService;

    @PostMapping
    public ResponseEntity<TokenIdDTO> login(@RequestBody LoginDTO loginDTO) {

        var tokenId = conCognitoService.login(loginDTO.username(), loginDTO.password());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenIdDTO(tokenId));
    }
}
