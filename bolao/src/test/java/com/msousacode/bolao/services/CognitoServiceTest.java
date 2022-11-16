package com.msousacode.bolao.services;

import com.msousacode.bolao.service.CognitoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CognitoServiceTest {

    @Autowired
    private CognitoService cognitoService;

    @Test
    public void teste() {

        cognitoService.login("msousacode@gmail.com", "NovaSenha@123");
    }
}
