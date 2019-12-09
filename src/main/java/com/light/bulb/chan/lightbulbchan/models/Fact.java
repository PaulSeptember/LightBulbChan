package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Fact {
    private String temp;
    private String feelsLike;
    private String condition;
    private String windSpeed;
    private String precType;
    private String precStrength;
    private String cloudness;
    private String season;

    public String getTemp() {
        return temp;
    }

    public Fact setTemp(String temp) {
        this.temp = temp;
        return this;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public Fact setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public Fact setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public Fact setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public String getPrecType() {
        return precType;
    }

    public Fact setPrecType(String precType) {
        this.precType = precType;
        return this;
    }

    public String getPrecStrength() {
        return precStrength;
    }

    public Fact setPrecStrength(String precStrength) {
        this.precStrength = precStrength;
        return this;
    }

    public String getCloudness() {
        return cloudness;
    }

    public Fact setCloudness(String cloudness) {
        this.cloudness = cloudness;
        return this;
    }

    public String getSeason() {
        return season;
    }

    public Fact setSeason(String season) {
        this.season = season;
        return this;
    }
}
