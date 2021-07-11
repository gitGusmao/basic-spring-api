package com.estudo.basic.services;

import com.estudo.basic.domain.Comentario;
import com.estudo.basic.domain.Livro;
import com.estudo.basic.exceptions.LivroException;
import com.estudo.basic.repository.ComentariosRepository;
import com.estudo.basic.repository.LivrosRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    LivrosRepository livrosRepository;
    ComentariosRepository comentariosRepository;

    public LivrosService(LivrosRepository livrosRepository, ComentariosRepository comentariosRepository) {
        this.livrosRepository = livrosRepository;
        this.comentariosRepository = comentariosRepository;
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

    public Comentario insertComment(Comentario comentario, Long id) {

        try {
            Optional<Livro> livro = this.find(id);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            comentario.setLivro(livro.orElse(null));
            comentario.setData(new Date());
            comentario.setUsuario(auth.getName());

        } catch (EmptyResultDataAccessException ex) {
            throw new LivroException("Livro não encontrado");
        }


        return comentariosRepository.save(comentario);
    }

    public List<Comentario> findComment(Long id) {
        Optional<Livro> book = livrosRepository.findById(id);

        if (!book.isPresent())
            throw new LivroException("Livro não encontrado");

        return book.get().getComentarios();
    }
}
