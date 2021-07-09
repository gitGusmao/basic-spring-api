package com.estudo.basic.resources;

import com.estudo.basic.domain.Livro;
import com.estudo.basic.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivrosResources {
    @Autowired
    LivrosRepository livrosRepository;

    @GetMapping(value = "/livros")
    public List<Livro> list() {
        return livrosRepository.findAll();
    }
}
