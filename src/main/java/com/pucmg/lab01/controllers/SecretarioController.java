package com.pucmg.lab01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab01.services.SecretarioService;

@RestController
public class SecretarioController {
    @Autowired
    SecretarioService secretarioService;
    
}
