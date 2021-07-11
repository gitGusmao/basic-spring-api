package com.estudo.basic.repository;

import com.estudo.basic.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {
}
