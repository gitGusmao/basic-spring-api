package com.estudo.basic.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class livrosResources {

    @GetMapping(value = "/livros")
    public String listar() {
        return "oi";
    }
}
