package br.com.ame.starwars.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
public class Planet {

    @PrimaryKey()
    private UUID id;
    private String name;
    private String climate;
    private String ground;
    private Long numAppearances;

    public Planet(){};


    private Planet(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.climate = builder.climate;
        this.ground = builder.ground;
        this.numAppearances = builder.numAppearances;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public Long getNumAppearances() {
        return numAppearances;
    }

    public void setNumAppearances(Long numAppearances) {
        this.numAppearances = numAppearances;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String climate;
        private String ground;
        private Long numAppearances;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder climate(String climate) {
            this.climate = climate;
            return this;
        }

        public Builder ground(String ground) {
            this.ground = ground;
            return this;
        }

        public Builder numAppearances(Long numAppearances) {
            this.numAppearances = numAppearances;
            return this;
        }
        public Planet build(){
            return new Planet(this);
        }
    }
}
