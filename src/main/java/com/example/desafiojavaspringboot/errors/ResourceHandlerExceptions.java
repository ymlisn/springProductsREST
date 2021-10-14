package com.example.desafiojavaspringboot.errors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceHandlerExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException exception) {

        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        StatusErrorResponse error = new StatusErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StatusErrorResponse> handleProductNotFound(ResponseStatusException exception){
        StatusErrorResponse error = new StatusErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getReason());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
