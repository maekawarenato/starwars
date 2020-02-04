package br.com.ame.starwars.service;

import br.com.ame.starwars.domain.Planet;
import br.com.ame.starwars.exception.PlanetAlreadyExistsException;
import br.com.ame.starwars.exception.PlanetNotFoundException;
import br.com.ame.starwars.model.PlanetRequest;
import br.com.ame.starwars.repository.PlanetRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlanetService {
    @Autowired
    private PlanetRepository planetRepository;

    public Flux<Planet> findPlanets(String name){
        return Optional.ofNullable(name)
                .map(planetRepository::findByName)
                .orElse(planetRepository.findAll());
    }

    public Mono<Planet> savePlanet(PlanetRequest request){

        return planetRepository.findByName(request.getName())
                .collectList()
                .flatMap(planets -> {
                    if(planets.isEmpty()) {
                        return planetRepository.save(buildPlanet(request));
                    }
                    throw new PlanetAlreadyExistsException();
                });

    }

    //TODO pegar da api o número de aparições
    public Planet buildPlanet(PlanetRequest request){
        return new Planet.Builder()
                .id(UUIDs.timeBased())
                .name(request.getName())
                .climate(request.getClimate())
                .ground(request.getGround())
                .build();
    }

    public Flux<Planet> deletePlanet(UUID id){
        return planetRepository.findById(id)
                .switchIfEmpty(Mono.just(new Planet()))
                .flatMapMany(planet -> {
                    if(planet == null || planet.getId() == null){
                        throw new PlanetNotFoundException();
                    }
                    return this.delete(planet)
                            .thenMany(planetRepository.findAll());
                });
    }

    private Mono<Void> delete(Planet planet){
        return planetRepository.delete(planet);
    }


}
