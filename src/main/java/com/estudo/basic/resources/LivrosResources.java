package com.estudo.basic.resources;

import com.estudo.basic.domain.Livro;
import com.estudo.basic.exceptions.LivroException;
import com.estudo.basic.repository.LivrosRepository;
import com.estudo.basic.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LivrosResources {

    LivrosRepository livrosRepository;

    LivrosService livrosService;

    public LivrosResources(LivrosRepository livrosRepository, LivrosService livrosService) {
        this.livrosRepository = livrosRepository;
        this.livrosService = livrosService;
    }

    @GetMapping(value = "/book")
    public ResponseEntity<List<Livro>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.list());
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<Optional<Livro>> find(@PathVariable("id") Long id) {

        Optional<Livro> book = livrosService.find(id);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Livro> insert(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livrosService.insert(livro));
    }

    @PutMapping(value = "/book/{id}")
    public ResponseEntity<Void> update(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livrosService.update(livro, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        livrosService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
