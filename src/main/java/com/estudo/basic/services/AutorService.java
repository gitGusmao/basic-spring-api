package com.estudo.basic.services;

import com.estudo.basic.domain.Autor;
import com.estudo.basic.exceptions.AutorException;
import com.estudo.basic.exceptions.NotFoundException;
import com.estudo.basic.repository.AutorRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> list() {
        return autorRepository.findAll();
    }

    public Autor insert(Autor autor) {

        if (autor.getId() != null) {
            Optional<Autor> autorFind = autorRepository.findById(autor.getId());

            if (autorFind.isPresent())
                throw new AutorException("Autor já cadastrado");
        }

        return autorRepository.save(autor);
    }

    public void update(Autor autor, Long id) {
        autor.setId(id);
        autorRepository.save(autor);
    }

    public Optional<Autor> find(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);

        if (!autor.isPresent())
            throw new NotFoundException("Autor não encontrado");

        return autor;
    }

    public void delete(@PathVariable("id") Long id) {
        try {
            autorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Autor não encontrado");
        }
    }
}
