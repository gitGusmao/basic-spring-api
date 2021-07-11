package com.estudo.basic.resources;

import com.estudo.basic.domain.Autor;
import com.estudo.basic.domain.Comentario;
import com.estudo.basic.domain.Livro;
import com.estudo.basic.repository.LivrosRepository;
import com.estudo.basic.services.AutorService;
import com.estudo.basic.services.LivrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutorResources {

    AutorService autorService;

    public AutorResources(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping(value = "/actor")
    public ResponseEntity<List<Autor>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(autorService.list());
    }

    @GetMapping(value = "/actor/{id}")
    public ResponseEntity<Optional<Autor>> find(@PathVariable("id") Long id) {

        Optional<Autor> book = autorService.find(id);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping(value = "/actor")
    public ResponseEntity<Autor> insert(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.insert(autor));
    }

    @PutMapping(value = "/actor/{id}")
    public ResponseEntity<Void> update(@RequestBody Autor livro, @PathVariable("id") Long id) {
        autorService.update(livro, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/actor/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        autorService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
