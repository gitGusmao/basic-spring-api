package com.estudo.basic.services;

import com.estudo.basic.domain.Livro;
import com.estudo.basic.exceptions.LivroException;
import com.estudo.basic.repository.LivrosRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    LivrosRepository livrosRepository;

    public LivrosService(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    public List<Livro> list() {
        return livrosRepository.findAll();
    }

    public Livro insert(Livro livro) {
        return livrosRepository.save(livro);
    }

    public void update(Livro livro, Long id) {
        livro.setId(id);
        livrosRepository.save(livro);
    }

    public Optional<Livro> find(Long id) {
        Optional<Livro> book = livrosRepository.findById(id);

        if (!book.isPresent())
            throw new LivroException("Livro não encontrado");

        return book;
    }

    public void delete(@PathVariable("id") Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new LivroException("Livro não encontrado");
        }
    }
}
