package br.com.ame.starwars.repository;

import br.com.ame.starwars.domain.Planet;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PlanetRepository extends ReactiveCassandraRepository<Planet, UUID> {
    @AllowFiltering
    public Flux<Planet> findByName(String name);
}
