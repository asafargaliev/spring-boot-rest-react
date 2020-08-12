package com.artems.insurance.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> exception(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

}
