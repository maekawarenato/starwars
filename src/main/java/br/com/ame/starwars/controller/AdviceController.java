package br.com.ame.starwars.controller;

import br.com.ame.starwars.exception.PlanetAlreadyExistsException;
import br.com.ame.starwars.exception.PlanetNotFoundException;
import br.com.ame.starwars.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({PlanetAlreadyExistsException.class})
    public ResponseEntity<Mono<ErrorResponse>> planetAlreadyExists(){
        ErrorResponse error = new ErrorResponse("Planet already exists", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .badRequest()
                .body(Mono.just(error));
    }

    @ExceptionHandler({PlanetNotFoundException.class})
    public ResponseEntity<Mono<ErrorResponse>> planetNotFound(){
        ErrorResponse error = new ErrorResponse("Planet not found", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .badRequest()
                .body(Mono.just(error));
    }
}
