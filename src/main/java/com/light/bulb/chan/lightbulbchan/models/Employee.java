package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private String fio;

    public String getFio() {
        return fio;
    }

    public Employee setFio(String fio) {
        this.fio = fio;
        return this;
    }
}
