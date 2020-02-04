package br.com.ame.starwars.model;

import javax.validation.constraints.NotBlank;

public class PlanetRequest {

    @NotBlank(message = "Name can not be empty")
    private String name;
    private String climate;
    private String ground;

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
}
