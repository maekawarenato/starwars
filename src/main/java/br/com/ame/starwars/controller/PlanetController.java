package br.com.ame.starwars.controller;

import br.com.ame.starwars.domain.Planet;
import br.com.ame.starwars.model.PlanetRequest;
import br.com.ame.starwars.repository.PlanetRepository;
import br.com.ame.starwars.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;
    @Autowired
    private PlanetRepository planetRepository;

    @GetMapping
    public ResponseEntity<Flux<Planet>> getAllPlanets(@RequestParam(value = "name", required = false) String name){

        return ResponseEntity
                .ok()
                .body(planetService.findPlanets(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Planet>> getPlanetById(@PathVariable(name = "id") UUID id){

        return ResponseEntity
                .ok()
                .body(planetRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<Planet>> savePlanet(@Valid @RequestBody PlanetRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(planetService.savePlanet(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flux<Planet>> deletePlanetById(@PathVariable(name = "id") UUID id){
        return ResponseEntity
                .ok()
                .body(planetService.deletePlanet(id));
    }
}
