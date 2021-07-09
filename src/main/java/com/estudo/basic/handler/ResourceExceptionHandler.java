package com.estudo.basic.handler;

import com.estudo.basic.exceptions.LivroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroException.class)
    public ResponseEntity<Void> handleBook404(LivroException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
