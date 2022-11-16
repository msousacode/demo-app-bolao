package com.msousacode.bolao.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping(path = "/api/hello")
    public String getResp() {
        return "Hey authenticated request";
    }
}