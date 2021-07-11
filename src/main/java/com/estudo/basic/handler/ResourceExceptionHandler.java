package com.estudo.basic.handler;

import com.estudo.basic.exceptions.AutorException;
import com.estudo.basic.exceptions.LivroException;
import com.estudo.basic.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handle404(NotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(LivroException.class)
    public ResponseEntity<Void> handleBook404(LivroException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(AutorException.class)
    public ResponseEntity<Void> handleAutor409(AutorException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
