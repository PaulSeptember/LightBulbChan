package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WeatherResponse {
    private Fact fact;

    public Fact getFact() {
        return fact;
    }

    public WeatherResponse setFact(Fact fact) {
        this.fact = fact;
        return this;
    }
}
